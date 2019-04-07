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

import cn.javakk.entity.Company;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.javakk.dao.CompanyDao;

/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class CompanyService {

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String SHORT_NAME = "shortName";
	private static final String INDUSTRY_TAG = "industryTag";
	private static final String COMPANY_SCALE_TAG = "companyScaleTag";
	private static final String FINANCING_TAG = "financingTag";
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Company> findAll() {
		return companyDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Company> findSearch(Map whereMap, int page, int size) {
		Specification<Company> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return companyDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Company> findSearch(Map whereMap) {
		Specification<Company> specification = createSpecification(whereMap);
		return companyDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Company findById(String id) {
		return companyDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param company
	 */
	public void add(Company company) {
		company.setId( idWorker.nextId()+"" );
		companyDao.save(company);
	}

	/**
	 * 修改
	 * @param company
	 */
	public void update(Company company) {
		companyDao.save(company);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		companyDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Company> createSpecification(Map searchMap) {

		return new Specification<Company>() {

			@Override
			public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                if (searchMap.get(ID)!=null && !"".equals(searchMap.get(ID))) {
                	predicateList.add(cb.like(root.get(ID).as(String.class), "%"+(String)searchMap.get(ID)+"%"));
                }
                // 名称
                if (searchMap.get(NAME)!=null && !"".equals(searchMap.get(NAME))) {
                	predicateList.add(cb.like(root.get(NAME).as(String.class), "%"+(String)searchMap.get(NAME)+"%"));
                }
                // 简称
                if (searchMap.get(SHORT_NAME)!=null && !"".equals(searchMap.get(SHORT_NAME))) {
                	predicateList.add(cb.like(root.get(SHORT_NAME).as(String.class), "%"+(String)searchMap.get(SHORT_NAME)+"%"));
                }
                // 行业字典
                if (searchMap.get(INDUSTRY_TAG)!=null && !"".equals(searchMap.get(INDUSTRY_TAG))) {
                	predicateList.add(cb.like(root.get(INDUSTRY_TAG).as(String.class), "%"+(String)searchMap.get(INDUSTRY_TAG)+"%"));
                }
                // 规模字典
                if (searchMap.get(COMPANY_SCALE_TAG)!=null && !"".equals(searchMap.get(COMPANY_SCALE_TAG))) {
                	predicateList.add(cb.like(root.get(COMPANY_SCALE_TAG).as(String.class), "%"+(String)searchMap.get(COMPANY_SCALE_TAG)+"%"));
                }
                // 融资字典
                if (searchMap.get(FINANCING_TAG)!=null && !"".equals(searchMap.get(FINANCING_TAG))) {
                	predicateList.add(cb.like(root.get(FINANCING_TAG).as(String.class), "%"+(String)searchMap.get(FINANCING_TAG)+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
