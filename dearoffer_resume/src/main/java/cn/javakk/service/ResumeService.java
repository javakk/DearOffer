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

import cn.javakk.entity.Resume;
import cn.javakk.util.IdWorker;
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
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Resume> findAll() {
		return resumeDao.findAll();
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Resume findById(String id) {
		return resumeDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param resume
	 */
	public void add(Resume resume) {
		resume.setId( idWorker.nextId()+"" );
		resumeDao.save(resume);
	}

	/**
	 * 修改
	 * @param resume
	 */
	public void update(Resume resume) {
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
