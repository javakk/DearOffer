package cn.javakk.service;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import cn.javakk.entity.Resume;
import cn.javakk.util.DateUtil;
import cn.javakk.util.IdWorker;
import cn.javakk.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.javakk.dao.ResumeDao;

/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class ResumeService {

	@Autowired
	private ResumeDao resumeDao;

	@Autowired
	private ProjectExperienceService projectExperienceService;

	@Autowired
	private RewardService rewardService;

	@Autowired
	private WorkExperienceService workExperienceService;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 简历查阅权限校验
	 * @param resumeId
	 * @return
	 */
	public Boolean canRead(String resumeId) {
		Long count = resumeDao.countByIdAndPublisherIdOrStatus(resumeId, UserThreadLocal.getUserId(), 2);
		return count > 0;
	}

	/**
	 * 简历修改权限校验
	 * @param resumeId
	 * @return
	 */
	public Boolean canWrite(String resumeId) {
		Long count = resumeDao.countByIdAndPublisherId(resumeId, UserThreadLocal.getUserId());
		return count > 0;
	}

	/**
	 * 查询我的全部列表
	 * @return
	 */
	public List<Resume> findAll() {
		return resumeDao.findByPublisherId(UserThreadLocal.getUserId());
	}

	/**
	 * 聚合简历详情
	 * @param id
	 * @return
	 */
	public Map findById(String id) {
		Map paramsMap = new HashMap(1);
		Map resultMap = new HashMap(3);
		paramsMap.put("resumeId", id);
		resultMap.put("reword", rewardService.findSearch(paramsMap));
		resultMap.put("projectExperience", projectExperienceService.findSearch(paramsMap));
		resultMap.put("workExperience", workExperienceService.findSearch(paramsMap));
		resultMap.put("resume", resumeDao.findById(id));
		return resultMap;
	}

	/**
	 * 增加简历
	 * @param resume
	 */
	public void add(Resume resume) {
		resume.setId( idWorker.nextId()+"" );
		resume.setCreateTime(DateUtil.getNow());
		resume.setPublisherId(UserThreadLocal.getUserId());
		//  默认不公开
		resume.setStatus(2);
		resumeDao.save(resume);
	}

	/**
	 * 修改
	 * @param resume
	 */
	public void update(Resume resume) {
		Resume resumeVO = resumeDao.findById(resume.getId()).get();
		resume.setPublisherId(resumeVO.getPublisherId());
		resume.setCreateTime(resumeVO.getCreateTime());
		resume.setModifyTime(DateUtil.getNow());
		resume.setStatus(resumeVO.getStatus());
		resumeDao.save(resume);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		resumeDao.deleteById(id);
	}


}
