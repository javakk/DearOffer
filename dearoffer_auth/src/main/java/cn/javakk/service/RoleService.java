package cn.javakk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.javakk.dao.RoleDao;
import cn.javakk.entity.Role;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class RoleService {

	private final String ID = "id";
	private final String NAME = "name";
	private final String DESCRIPTION = "description";

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Role> findSearch(Map whereMap, int page, int size) {
		Specification<Role> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return roleDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Role> findSearch(Map whereMap) {
		Specification<Role> specification = createSpecification(whereMap);
		return roleDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Role findById(String id) {
		return roleDao.findById(id).get();
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Role> createSpecification(Map searchMap) {

		return new Specification<Role>() {

			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 
                if (searchMap.get(ID)!=null && !"".equals(searchMap.get(ID))) {
                	predicateList.add(cb.like(root.get(ID).as(String.class), "%"+(String)searchMap.get(ID)+"%"));
                }
                // 角色名称
                if (searchMap.get(NAME)!=null && !"".equals(searchMap.get(NAME))) {
                	predicateList.add(cb.like(root.get(NAME).as(String.class), "%"+(String)searchMap.get(NAME)+"%"));
                }
                // 详情描述
                if (searchMap.get(DESCRIPTION)!=null && !"".equals(searchMap.get(DESCRIPTION))) {
                	predicateList.add(cb.like(root.get(DESCRIPTION).as(String.class), "%"+(String)searchMap.get(DESCRIPTION)+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
