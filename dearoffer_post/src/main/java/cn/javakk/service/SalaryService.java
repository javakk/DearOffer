package cn.javakk.service;

import cn.javakk.dao.SalaryDao;
import cn.javakk.pojo.Salary;
import cn.javakk.util.DateUtil;
import cn.javakk.util.IdWorker;
import cn.javakk.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	private static final String CITY = "city";
	private static final String COMPANY_NAME = "companyName";
	private static final String POSITION_TITLE = "positionTitle";
	private static final String DETAIL = "detail";

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
		Sort sort = new Sort(Sort.Direction.DESC, "createTime");
		PageRequest pageRequest =  PageRequest.of(page-1, size,sort);
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
                if (searchMap.get(COMPANY_NAME)!=null && !"".equals(searchMap.get(COMPANY_NAME))) {
                	predicateList.add(cb.like(root.get(COMPANY_NAME).as(String.class), "%"+(String)searchMap.get(COMPANY_NAME)+"%"));
                }
                // 所属公司
                if (searchMap.get(COMPANY_ID)!=null && !"".equals(searchMap.get(COMPANY_ID))) {
                	predicateList.add(cb.like(root.get(COMPANY_ID).as(String.class), "%"+(String)searchMap.get(COMPANY_ID)+"%"));
                }
                // 所属城市
                if (searchMap.get(CITY)!=null && !"".equals(searchMap.get(CITY))) {
                	predicateList.add(cb.like(root.get(CITY).as(String.class), "%"+(String)searchMap.get(CITY)+"%"));
                }
                // 职位名称
                if (searchMap.get(POSITION_TITLE)!=null && !"".equals(searchMap.get(POSITION_TITLE))) {
                	predicateList.add(cb.like(root.get(POSITION_TITLE).as(String.class), "%"+(String)searchMap.get(POSITION_TITLE)+"%"));
                }
                // 细节
                if (searchMap.get(DETAIL)!=null && !"".equals(searchMap.get(DETAIL))) {
                	predicateList.add(cb.like(root.get(DETAIL).as(String.class), "%"+(String)searchMap.get(DETAIL)+"%"));
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
