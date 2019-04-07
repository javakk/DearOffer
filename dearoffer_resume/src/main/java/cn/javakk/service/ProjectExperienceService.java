package cn.javakk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.javakk.entity.ProjectExperience;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.javakk.dao.ProjectExperienceDao;

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

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<ProjectExperience> findAll() {
		return projectExperienceDao.findAll();
	}


	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public ProjectExperience findById(String id) {
		return projectExperienceDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param projectExperience
	 */
	public void add(ProjectExperience projectExperience) {
		projectExperience.setId( idWorker.nextId()+"" );
		projectExperienceDao.save(projectExperience);
	}

	/**
	 * 修改
	 * @param projectExperience
	 */
	public void update(ProjectExperience projectExperience) {
		projectExperienceDao.save(projectExperience);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		projectExperienceDao.deleteById(id);
	}


}
