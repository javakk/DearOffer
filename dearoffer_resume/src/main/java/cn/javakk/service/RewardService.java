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

import cn.javakk.entity.Reward;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.javakk.dao.RewardDao;

/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class RewardService {

	@Autowired
	private RewardDao rewardDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Reward> findAll() {
		return rewardDao.findAll();
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Reward findById(String id) {
		return rewardDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param reward
	 */
	public void add(Reward reward) {
		reward.setId( idWorker.nextId()+"" );
		rewardDao.save(reward);
	}

	/**
	 * 修改
	 * @param reward
	 */
	public void update(Reward reward) {
		rewardDao.save(reward);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		rewardDao.deleteById(id);
	}


}
