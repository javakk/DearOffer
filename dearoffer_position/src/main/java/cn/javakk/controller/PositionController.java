package cn.javakk.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javakk.entity.*;
import cn.javakk.service.AppliedInfoService;
import cn.javakk.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import cn.javakk.service.PositionService;

/**
 * 控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/position")
public class PositionController {

	@Autowired
	private PositionService positionService;

	@Autowired
	private AppliedInfoService appliedInfoService;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@GetMapping(value="/{id}")
	public Result findById(@PathVariable String id){
		Position position = positionService.findById(id);
		// TODO：记录点击量
		String userId = UserThreadLocal.getUserId();
		if (position != null) {
			redisTemplate.opsForList().rightPush("position:view:" + id, userId);
		}
		return new Result(true,StatusCode.OK,"查询成功", position);
	}


	/**
	 * 申请职位
	 * @param id
	 */
	@PutMapping(value="/{id}")
	public Result update(@PathVariable String id){
		String userId = UserThreadLocal.getUserId();
		// 查询是否已申请
		Map searchMap = new HashMap<String, Object>(2);
		searchMap.put("userId", userId);
		searchMap.put("positionId", id);

		List<AppliedInfo> appliedInfos = appliedInfoService.findSearch(searchMap);

		if (appliedInfos != null && appliedInfos.size() > 0) {
			return new Result(false, StatusCode.ERROR, "已经安排上了");
		}

		positionService.apply(id, userId);

		return new Result(true,StatusCode.OK,"申请成功");
	}

	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@PostMapping(value="/search/{page}/{size}")
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		// TODO:处理UserID是否一致
		Page<Position> pageList = positionService.findSearch(searchMap, page, size);
		return new Result(true,StatusCode.OK,"查询成功",  new PageResult<Position>(pageList.getTotalElements(), pageList.getContent()) );
	}
}
