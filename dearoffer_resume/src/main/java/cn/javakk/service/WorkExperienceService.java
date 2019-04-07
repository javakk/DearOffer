package cn.javakk.service;

import java.util.List;

import cn.javakk.entity.WorkExperience;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.javakk.dao.WorkExperienceDao;

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
		workExperienceDao.save(workExperience);
	}

	/**
	 * 修改
	 * @param workExperience
	 */
	public void update(WorkExperience workExperience) {
		workExperienceDao.save(workExperience);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		workExperienceDao.deleteById(id);
	}

}
