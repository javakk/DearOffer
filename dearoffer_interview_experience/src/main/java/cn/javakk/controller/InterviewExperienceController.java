package cn.javakk.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javakk.entity.InterviewExperience;
import cn.javakk.entity.PageResult;
import cn.javakk.entity.Result;
import cn.javakk.entity.StatusCode;
import cn.javakk.service.InterviewExperienceService;
import cn.javakk.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/InterviewExperience")
public class InterviewExperienceController {

	@Autowired
	private InterviewExperienceService interviewExperienceService;
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 根据公司id展示
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/company/{companyId}/{page}/{size}",method= RequestMethod.GET)
	public Result findByCompany(@PathVariable String companyId, @PathVariable int page, @PathVariable int size){
		Map searchMap = new HashMap<String, Object> (1);
		Page<InterviewExperience> pageList = interviewExperienceService.findSearch(searchMap, page, size);
		searchMap.put("companyId", companyId);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<InterviewExperience>(pageList.getTotalElements(), pageList.getContent()));
	}


	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",interviewExperienceService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<InterviewExperience> pageList = interviewExperienceService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<InterviewExperience>(pageList.getTotalElements(), pageList.getContent()) );
	}
	
	/**
	 * 增加
	 * @param interviewExperience
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody InterviewExperience interviewExperience) {
		// TODO:记录userId
		interviewExperienceService.add(interviewExperience);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 点赞
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@PathVariable String id ){
		// TODO:userId变更
		String userId = UserThreadLocal.getUserId();
		// Redis键前缀
		String likedKey = "interview:liked:" + id;
		if ( redisTemplate.opsForSet().isMember(likedKey, userId)) {
			return new Result(false, StatusCode.ERROR, "您已经赞过");
		}

		interviewExperienceService.updateLikedCount(id);
		redisTemplate.opsForSet().add(likedKey, userId);
		return new Result(true,StatusCode.OK,"点赞成功");
		
	}
	
}
