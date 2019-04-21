package cn.javakk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.javakk.dao.UserDao;
import cn.javakk.pojo.User;
import cn.javakk.util.DateUtil;
import cn.javakk.util.IdWorker;
import cn.javakk.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class UserService {

	private static final String USERNAME = "userName";
	private static final String ID = "id";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public User findById(String id) {
        Optional<User> option = userDao.findById(id);
        if (option != null && option.isPresent()) {
            return option.get();
        }
        return null;
	}


	/**
	 * 修改
	 * @param user
	 */
	public void update(User user) {
		Optional<User> option = userDao.findById(user.getId());
		if (option != null && option.isPresent()) {
			User userVO = option.get();
			if (!StringUtils.isEmpty(user.getPassword())) {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			}
			user.setRoleId(userVO.getRoleId());
			user.setModifyTime(DateUtil.getNow());
			user.setCreateTime(userVO.getCreateTime());
			user.setStatus(userVO.getStatus());
			user.setPhone(userVO.getPhone());
			userDao.saveAndFlush(user);
		}
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		userDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 
                if (searchMap.get(ID)!=null && !"".equals(searchMap.get(ID))) {
                	predicateList.add(cb.like(root.get(ID).as(String.class), "%"+(String)searchMap.get(ID)+"%"));
                }
                // 用户名
                if (searchMap.get(USERNAME)!=null && !"".equals(searchMap.get(USERNAME))) {
                	predicateList.add(cb.like(root.get(USERNAME).as(String.class), "%"+(String)searchMap.get(USERNAME)+"%"));
                }
                // 密码
                if (searchMap.get(PASSWORD)!=null && !"".equals(searchMap.get(PASSWORD))) {
                	predicateList.add(cb.like(root.get(PASSWORD).as(String.class), "%"+(String)searchMap.get(PASSWORD)+"%"));
                }
                // 邮箱
                if (searchMap.get(EMAIL)!=null && !"".equals(searchMap.get(EMAIL))) {
                	predicateList.add(cb.like(root.get(EMAIL).as(String.class), "%"+(String)searchMap.get(EMAIL)+"%"));
                }
                // 电话
                if (searchMap.get(PHONE)!=null && !"".equals(searchMap.get(PHONE))) {
                	predicateList.add(cb.like(root.get(PHONE).as(String.class), "%"+(String)searchMap.get(PHONE)+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

	/**
	 * 用户注册
	 * @param user
	 */
    public void add(User user) {
		String salt = RandomUtil.generateSalt();
		user.setId(idWorker.nextId() + "");
		user.setCreateTime(DateUtil.getNow());
    	user.setSalt(salt);
    	user.setStatus(1);
    	// TODO:role
    	user.setRoleId("1");
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDao.save(user);
    }

}
