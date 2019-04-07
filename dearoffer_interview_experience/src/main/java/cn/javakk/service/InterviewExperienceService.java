package cn.javakk.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.javakk.entity.InterviewExperience;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.javakk.dao.InterviewExperienceDao;

/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class InterviewExperienceService {

	private static final String EXPERIENCE_TAG = "experienceTag";
	private static final String CONTENT = "content";
	@Autowired
	private InterviewExperienceDao interviewExperienceDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<InterviewExperience> findAll() {
		return interviewExperienceDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<InterviewExperience> findSearch(Map whereMap, int page, int size) {
		Specification<InterviewExperience> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return interviewExperienceDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<InterviewExperience> findSearch(Map whereMap) {
		Specification<InterviewExperience> specification = createSpecification(whereMap);
		return interviewExperienceDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public InterviewExperience findById(String id) {
		return interviewExperienceDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param interviewExperience
	 */
	public void add(InterviewExperience interviewExperience) {
		interviewExperience.setId( idWorker.nextId()+"" );
		interviewExperienceDao.save(interviewExperience);
	}

	/**
	 * 修改
	 * @param interviewExperience
	 */
	public void update(InterviewExperience interviewExperience) {
		interviewExperienceDao.save(interviewExperience);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		interviewExperienceDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<InterviewExperience> createSpecification(Map searchMap) {

		return new Specification<InterviewExperience>() {

			@Override
			public Predicate toPredicate(Root<InterviewExperience> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 面经字典
                if (searchMap.get(EXPERIENCE_TAG)!=null && !"".equals(searchMap.get(EXPERIENCE_TAG))) {
                	predicateList.add(cb.like(root.get(EXPERIENCE_TAG).as(String.class), "%"+(String)searchMap.get(EXPERIENCE_TAG)+"%"));
                }
                // 内容
                if (searchMap.get(CONTENT)!=null && !"".equals(searchMap.get(CONTENT))) {
                	predicateList.add(cb.like(root.get(CONTENT).as(String.class), "%"+(String)searchMap.get(CONTENT)+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}