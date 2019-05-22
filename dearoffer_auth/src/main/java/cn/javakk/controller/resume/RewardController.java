package cn.javakk.controller.resume;

import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.pojo.resume.Reward;
import cn.javakk.service.resume.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
