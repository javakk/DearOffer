package cn.javakk.service;

import cn.javakk.dao.SalaryDao;
import cn.javakk.pojo.Salary;
import cn.javakk.crawler.util.DateUtil;
import cn.javakk.crawler.util.IdWorker;
import cn.javakk.crawler.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class SalaryService {

	private static final String COMPANY_ID = "companyId";
	private static final String ID = "id";
	private static final String CITY = "city";
	private static final String DEGREE_TAG = "degreeTag";
	private static final String POSITION_TITLE = "positionTitle";
	private static final String SKILL_TAG = "skillTag";

	@Value("${redis.keys.salary_credibility_fix}")
	private String credibilityKeyFix;

	@Autowired
	private SalaryDao salaryDao;
	
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Salary> findAll() {
		return salaryDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Salary> findSearch(Map whereMap, int page, int size) {
		Specification<Salary> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return salaryDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Salary> findSearch(Map whereMap) {
		Specification<Salary> specification = createSpecification(whereMap);
		return salaryDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Salary findById(String id) {
		Salary salary = null;
		Optional<Salary> optional = salaryDao.findById(id);
		if (optional != null && optional.isPresent()) {
			salary = optional.get();
			salary.setPageView(salary.getPageView() + 1);
			// 可信度封装
			Long allCount = redisTemplate.opsForSet().size(credibilityKeyFix + id);
			allCount = allCount == null ? 1 : allCount;
			salary.setCredibilityPercent(salary.getCredibility() * 1F / allCount / 100);
			salaryDao.save(salary);
		}

		return salary;
	}

	/**
	 * 增加
	 * @param salary
	 */
	public void add(Salary salary) {
		salary.setId( idWorker.nextId()+"" );
		salary.setCreateTime(DateUtil.getNow());
		salary.setPageView(1L);
		salary.setPublisherId(UserThreadLocal.getUserId());
		salary.setCredibility(50);
		salary.setStatus(1);
		salaryDao.save(salary);
	}

	/**
	 * 修改
	 * @param salary
	 */
	public void update(Salary salary) {
		salaryDao.save(salary);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Salary> createSpecification(Map searchMap) {

		return new Specification<Salary>() {

			@Override
			public Predicate toPredicate(Root<Salary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                if (searchMap.get(ID)!=null && !"".equals(searchMap.get(ID))) {
                	predicateList.add(cb.like(root.get(ID).as(String.class), "%"+(String)searchMap.get(ID)+"%"));
                }
                // 所属公司
                if (searchMap.get(COMPANY_ID)!=null && !"".equals(searchMap.get(COMPANY_ID))) {
                	predicateList.add(cb.like(root.get(COMPANY_ID).as(String.class), "%"+(String)searchMap.get(COMPANY_ID)+"%"));
                }
                // 学历字典
                if (searchMap.get(DEGREE_TAG)!=null && !"".equals(searchMap.get(DEGREE_TAG))) {
                	predicateList.add(cb.like(root.get(DEGREE_TAG).as(String.class), "%"+(String)searchMap.get(DEGREE_TAG)+"%"));
                }
                // 所属城市
                if (searchMap.get(CITY)!=null && !"".equals(searchMap.get(CITY))) {
                	predicateList.add(cb.like(root.get(CITY).as(String.class), "%"+(String)searchMap.get(CITY)+"%"));
                }
                // 职位名称
                if (searchMap.get(POSITION_TITLE)!=null && !"".equals(searchMap.get(POSITION_TITLE))) {
                	predicateList.add(cb.like(root.get(POSITION_TITLE).as(String.class), "%"+(String)searchMap.get(POSITION_TITLE)+"%"));
                }
                // 技能标签
                if (searchMap.get(SKILL_TAG)!=null && !"".equals(searchMap.get(SKILL_TAG))) {
                	predicateList.add(cb.like(root.get(SKILL_TAG).as(String.class), "%"+(String)searchMap.get(SKILL_TAG)+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}


	public void updateCredibility(String id, Boolean agree) {
		Optional<Salary> option = salaryDao.findById(id);
		if (option != null && option.isPresent()) {
			Salary salaryDO = option.get();
			Integer optionCount = agree ? 1 : -1;
			salaryDO.setCredibility(salaryDO.getCredibility() + optionCount);
			salaryDao.save(salaryDO);
		}
	}
}
