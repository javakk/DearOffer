package cn.javakk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.javakk.dao.TeachInDao;
import cn.javakk.pojo.TeachIn;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class TeachInService {

	private final String COMPANY_NAME = "companyName";
	private final String SCHOOL_NAME = "schoolName";



	@Autowired
	private TeachInDao teachinDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<TeachIn> findAll() {
		return teachinDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TeachIn> findSearch(Map whereMap, int page, int size) {
		Specification<TeachIn> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return teachinDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<TeachIn> findSearch(Map whereMap) {
		Specification<TeachIn> specification = createSpecification(whereMap);
		return teachinDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public TeachIn findById(String id) {
		return teachinDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param teachIn
	 */
	public void add(TeachIn teachIn) {
		teachIn.setId( idWorker.nextId()+"" );
		teachinDao.save(teachIn);
	}

	/**
	 * 修改
	 * @param teachIn
	 */
	public void update(TeachIn teachIn) {
		teachinDao.save(teachIn);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		teachinDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<TeachIn> createSpecification(Map searchMap) {

		return new Specification<TeachIn>() {

			@Override
			public Predicate toPredicate(Root<TeachIn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 公司名
                if (searchMap.get(COMPANY_NAME)!=null && !"".equals(searchMap.get(COMPANY_NAME))) {
                	predicateList.add(cb.like(root.get(COMPANY_NAME).as(String.class), "%"+(String)searchMap.get(COMPANY_NAME)+"%"));
                }
                // 学校名
                if (searchMap.get(SCHOOL_NAME)!=null && !"".equals(searchMap.get(SCHOOL_NAME))) {
                	predicateList.add(cb.like(root.get(SCHOOL_NAME).as(String.class), "%"+(String)searchMap.get(SCHOOL_NAME)+"%"));
                }
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
