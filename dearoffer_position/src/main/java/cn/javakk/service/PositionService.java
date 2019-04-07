package cn.javakk.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import cn.javakk.entity.Position;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.javakk.dao.PositionDao;

/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class PositionService {

	private static final String POSITION_TAG = "positionTag";
	private static final String CITY_TAG = "cityTag";
	private static final String COMPANY_ID = "companyId";
	private static final String TITLE = "title";
	@Autowired
	private PositionDao positionDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Position> findAll() {
		return positionDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Position> findSearch(Map whereMap, int page, int size) {
		Specification<Position> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return positionDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Position> findSearch(Map whereMap) {
		Specification<Position> specification = createSpecification(whereMap);
		return positionDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Position findById(String id) {
		return positionDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param position
	 */
	public void add(Position position) {
		position.setId( idWorker.nextId()+"" );
		positionDao.save(position);
	}

	/**
	 * 修改
	 * @param position
	 */
	public void update(Position position) {
		positionDao.save(position);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		positionDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Position> createSpecification(Map searchMap) {

		return new Specification<Position>() {

			@Override
			public Predicate toPredicate(Root<Position> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 职位类型字典
                if (searchMap.get(POSITION_TAG)!=null && !"".equals(searchMap.get(POSITION_TAG))) {
                	predicateList.add(cb.like(root.get(POSITION_TAG).as(String.class), "%"+(String)searchMap.get(POSITION_TAG)+"%"));
                }
                // 所在城市字典
                if (searchMap.get(CITY_TAG)!=null && !"".equals(searchMap.get(CITY_TAG))) {
                	predicateList.add(cb.like(root.get(CITY_TAG).as(String.class), "%"+(String)searchMap.get(CITY_TAG)+"%"));
                }
                // 公司
                if (searchMap.get(COMPANY_ID)!=null && !"".equals(searchMap.get(COMPANY_ID))) {
                	predicateList.add(cb.like(root.get(COMPANY_ID).as(String.class), "%"+(String)searchMap.get(COMPANY_ID)+"%"));
                }
                // 职位名称
                if (searchMap.get(TITLE)!=null && !"".equals(searchMap.get(TITLE))) {
                	predicateList.add(cb.like(root.get(TITLE).as(String.class), "%"+(String)searchMap.get(TITLE)+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
