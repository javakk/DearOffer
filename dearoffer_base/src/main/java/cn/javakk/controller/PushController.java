package cn.javakk.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javakk.pojo.PageResult;
import cn.javakk.pojo.Push;
import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import cn.javakk.service.PushService;

/**
 * 控制器层
 * @author JavaKK
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/push")
public class PushController {

	@Autowired
	private PushService pushService;

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",pushService.findById(id));
	}


	/**
	 * 获取今日推送
	 * @return
	 */
	@GetMapping(value="/")
	public Result todayPush(){
 		List<Push> pushes = pushService.findToday();
		return  new Result(true,StatusCode.OK,"查询成功",  pushes);
	}


}
