package cn.javakk.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.javakk.pojo.Push;
import cn.javakk.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import cn.javakk.dao.PushDao;

/**
 * 服务层
 * 
 * @author JavaKK
 *
 */
@Service
public class PushService {

	private final String START_TIME = "startTime";
	private final String END_TIME = "endTime";

	@Autowired
	private PushDao pushDao;


	@Autowired
	private RedisTemplate redisTemplate;

	@Value("${redis.keys.push_items}")
	private String pushKey;


	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Push findById(String id) {
		return pushDao.findById(id).get();
	}


	public List<Push> findToday() {
		List<Push> pushes = (List<Push>) redisTemplate.opsForValue().get(pushKey);
		if (pushes == null || pushes.isEmpty()) {
			pushes = pushDao.findTodayPush(DateUtil.getNow(), DateUtil.getNow());
			redisTemplate.opsForValue().set(pushKey, pushes);
			// 定时任务每天0点刷掉Redis
			redisTemplate.expire(pushKey, 1L, TimeUnit.MINUTES);
		}
		return pushes;
	}

}
