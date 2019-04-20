package cn.javakk.controller;
import java.util.List;
import java.util.Map;

import cn.javakk.entity.Result;
import cn.javakk.entity.Reward;
import cn.javakk.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.javakk.service.RewardService;

/**
 * 控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/reward")
public class RewardController {

	@Autowired
	private RewardService rewardService;

	/**
	 * 增加
	 * @param reward
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Reward reward){
		rewardService.add(reward);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param reward
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Reward reward, @PathVariable String id ){
		Boolean canWrite = rewardService.canWrite(id);
		if (!canWrite) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		reward.setId(id);
		rewardService.update(reward);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		Boolean canWrite = rewardService.canWrite(id);
		if (!canWrite) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		rewardService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
}
