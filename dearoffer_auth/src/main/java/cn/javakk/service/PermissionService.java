package cn.javakk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.javakk.dao.PermissionDao;
import cn.javakk.pojo.Permission;
import cn.javakk.crawler.util.IdWorker;
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
public class PermissionService {

	private final String ID = "id";
	private final String ROLE_ID = "role_id";
	private final String PERMISSION_ID = "permission_id";

	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Permission> findSearch(Map whereMap, int page, int size) {
		Specification<Permission> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return permissionDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Permission> findSearch(Map whereMap) {
		Specification<Permission> specification = createSpecification(whereMap);
		return permissionDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Permission findById(String id) {
		return permissionDao.findById(id).get();
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Permission> createSpecification(Map searchMap) {

		return new Specification<Permission>() {

			@Override
			public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 
                if (searchMap.get(ID)!=null && !"".equals(searchMap.get(ID))) {
                	predicateList.add(cb.like(root.get(ID).as(String.class), "%"+(String)searchMap.get(ID)+"%"));
                }
                // 角色id
                if (searchMap.get(ROLE_ID)!=null && !"".equals(searchMap.get(ROLE_ID))) {
                	predicateList.add(cb.like(root.get(ROLE_ID).as(String.class), "%"+(String)searchMap.get(ROLE_ID)+"%"));
                }
                // 权限id
                if (searchMap.get(PERMISSION_ID)!=null && !"".equals(searchMap.get(PERMISSION_ID))) {
                	predicateList.add(cb.like(root.get(PERMISSION_ID).as(String.class), "%"+(String)searchMap.get(PERMISSION_ID)+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
