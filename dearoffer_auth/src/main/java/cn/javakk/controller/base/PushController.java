package cn.javakk.controller.base;

import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.pojo.base.Push;
import cn.javakk.service.base.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
