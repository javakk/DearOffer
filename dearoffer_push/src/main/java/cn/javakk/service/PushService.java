package cn.javakk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.javakk.pojo.Push;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.javakk.dao.PushDao;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class PushService {

	private static final String ROLE_ID = "roleId";
	@Autowired
	private PushDao pushDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Push> findAll() {
		return pushDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Push> findSearch(Map whereMap, int page, int size) {
		Specification<Push> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return pushDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Push> findSearch(Map whereMap) {
		Specification<Push> specification = createSpecification(whereMap);
		return pushDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Push findById(String id) {
		return pushDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param push
	 */
	public void add(Push push) {
		push.setId( idWorker.nextId()+"" );
		pushDao.save(push);
	}

	/**
	 * 修改
	 * @param push
	 */
	public void update(Push push) {
		pushDao.save(push);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		pushDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Push> createSpecification(Map searchMap) {

		return new Specification<Push>() {

			@Override
			public Predicate toPredicate(Root<Push> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                
                // 推送角色
                if (searchMap.get(ROLE_ID)!=null && !"".equals(searchMap.get(ROLE_ID))) {
                	predicateList.add(cb.like(root.get(ROLE_ID).as(String.class), "%"+(String)searchMap.get(ROLE_ID)+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
