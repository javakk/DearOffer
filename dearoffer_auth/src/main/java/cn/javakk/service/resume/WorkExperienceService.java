package cn.javakk.service.resume;


import cn.javakk.dao.resume.WorkExperienceDao;
import cn.javakk.pojo.resume.WorkExperience;
import cn.javakk.util.DateUtil;
import cn.javakk.util.IdWorker;
import cn.javakk.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class WorkExperienceService {

	@Autowired
	private WorkExperienceDao workExperienceDao;
	
	@Autowired
	private IdWorker idWorker;
	private final String PUBLISHER_ID = "publisherId";
	private final String RESUME_ID = "resumeId";

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<WorkExperience> findAll() {
		return workExperienceDao.findAll();
	}


	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public WorkExperience findById(String id) {
		return workExperienceDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param workExperience
	 */
	public void add(WorkExperience workExperience) {
		workExperience.setId( idWorker.nextId()+"" );
		workExperience.setCreateTime(DateUtil.getNow());
		workExperience.setStatus(1);
		workExperience.setPublisherId(UserThreadLocal.getUserId());
		workExperienceDao.save(workExperience);
	}

	/**
	 * 修改
	 * @param workExperience
	 */
	public void update(WorkExperience workExperience) {
		WorkExperience workExperienceVO = workExperienceDao.findById(workExperience.getId()).get();
		workExperience.setPublisherId(workExperienceVO.getPublisherId());
		workExperience.setCreateTime(workExperienceVO.getCreateTime());
		workExperience.setResumeId(workExperienceVO.getResumeId());
		workExperience.setModifyTime(DateUtil.getNow());
		workExperience.setStatus(workExperience.getStatus());
		workExperience.setResumeId(workExperienceVO.getResumeId());
		workExperienceDao.save(workExperience);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		workExperienceDao.deleteById(id);
	}

	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<WorkExperience> findSearch(Map whereMap) {
		Specification<WorkExperience> specification = createSpecification(whereMap);
		return workExperienceDao.findAll(specification);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<WorkExperience> createSpecification(Map searchMap) {
		return new Specification<WorkExperience> () {
			@Override
			public Predicate toPredicate(Root<WorkExperience> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
	 * @param resumeId
	 * @return
	 */
	public Boolean canWrite(String resumeId) {
		Long count = workExperienceDao.countByIdAndPublisherId(resumeId, UserThreadLocal.getUserId());
		return count > 0;
	}

}
