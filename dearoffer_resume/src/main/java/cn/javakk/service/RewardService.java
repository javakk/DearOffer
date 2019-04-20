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
import cn.javakk.util.DateUtil;
import cn.javakk.util.IdWorker;
import cn.javakk.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.javakk.dao.RewardDao;
import org.springframework.util.StringUtils;

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

	private final String PUBLISHER_ID = "publisherId";
	private final String RESUME_ID = "resumeId";

	public Boolean canWrite(String rewardId) {
		Long count = rewardDao.countByIdAndPublisherId(rewardId, UserThreadLocal.getUserId());
		return count > 0;
	}

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
		reward.setPublisherId(UserThreadLocal.getUserId());
		reward.setCreateTime(DateUtil.getNow());
		reward.setStatus(1);
		rewardDao.save(reward);
	}

	/**
	 * 修改
	 * @param reward
	 */
	public void update(Reward reward) {
		Reward rewardVO = rewardDao.findById(reward.getId()).get();
		reward.setStatus(rewardVO.getStatus());
		reward.setModifyTime(DateUtil.getNow());
		reward.setCreateTime(rewardVO.getCreateTime());
		reward.setPublisherId(rewardVO.getPublisherId());
		reward.setResumeId(rewardVO.getResumeId());
		rewardDao.save(reward);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		rewardDao.deleteById(id);
	}



	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Reward> findSearch(Map whereMap) {
		Specification<Reward> specification = createSpecification(whereMap);
		return rewardDao.findAll(specification);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Reward> createSpecification(Map searchMap) {
		return new Specification<Reward> () {
			@Override
			public Predicate toPredicate(Root<Reward> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
}
