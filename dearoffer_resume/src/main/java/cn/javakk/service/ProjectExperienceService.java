package cn.javakk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.javakk.pojo.ProjectExperience;
import cn.javakk.crawler.util.DateUtil;
import cn.javakk.crawler.util.IdWorker;
import cn.javakk.crawler.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.javakk.dao.ProjectExperienceDao;
import org.springframework.util.StringUtils;

/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class ProjectExperienceService {

	@Autowired
	private ProjectExperienceDao projectExperienceDao;
	
	@Autowired
	private IdWorker idWorker;
	private final String PUBLISHER_ID = "publisherId";
	private final String RESUME_ID = "resumeId";
	
	/**
	 * 增加
	 * @param projectExperience
	 */
	public void add(ProjectExperience projectExperience) {
		projectExperience.setId( idWorker.nextId()+"" );
		projectExperience.setCreateTime(DateUtil.getNow());
		projectExperience.setStatus(1);
		projectExperience.setPublisherId(UserThreadLocal.getUserId());
		projectExperienceDao.save(projectExperience);
	}

	/**
	 * 修改
	 * @param projectExperience
	 */
	public void update(ProjectExperience projectExperience) {
		ProjectExperience projectExperienceVO = projectExperienceDao.findById(projectExperience.getId()).get();
		projectExperience.setPublisherId(projectExperienceVO.getPublisherId());
		projectExperience.setCreateTime(projectExperienceVO.getCreateTime());
		projectExperience.setResumeId(projectExperienceVO.getResumeId());
		projectExperience.setModifyTime(DateUtil.getNow());
		projectExperience.setStatus(projectExperienceVO.getStatus());
		projectExperienceDao.save(projectExperience);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		projectExperienceDao.deleteById(id);
	}

	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<ProjectExperience> findSearch(Map whereMap) {
		Specification<ProjectExperience> specification = createSpecification(whereMap);
		return projectExperienceDao.findAll(specification);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<ProjectExperience> createSpecification(Map searchMap) {
		return new Specification<ProjectExperience> () {
			@Override
			public Predicate toPredicate(Root<ProjectExperience> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(searchMap.get(PUBLISHER_ID))) {
					predicateList.add(criteriaBuilder.equal(root.get(PUBLISHER_ID), searchMap.get(PUBLISHER_ID)));
				}
				if (!StringUtils.isEmpty(searchMap.get(RESUME_ID))) {
					predicateList.add(criteriaBuilder.equal(root.get(RESUME_ID), searchMap.get(RESUME_ID)));
				}
				return criteriaBuilder.and(predicateList.toArray(new Predicate [predicateList.size()]));
			}
		};
	}

	/**
	 * 修改权限校验
	 * @param projectId
	 * @return
	 */
	public Boolean canWrite(String projectId) {
		Long count = projectExperienceDao.countByIdAndPublisherId(projectId, UserThreadLocal.getUserId());
		return count > 0;
	}


}
