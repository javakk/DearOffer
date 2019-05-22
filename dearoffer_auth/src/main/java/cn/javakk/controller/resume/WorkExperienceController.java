package cn.javakk.controller.resume;

import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.pojo.resume.WorkExperience;
import cn.javakk.service.resume.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/workExperience")
public class WorkExperienceController {

	@Autowired
	private WorkExperienceService workexperienceService;

	/**
	 * 增加
	 * @param workExperience
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody WorkExperience workExperience  ){
		workexperienceService.add(workExperience);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param workExperience
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody WorkExperience workExperience, @PathVariable String id ){
		Boolean canWrite = workexperienceService.canWrite(id);
		if (!canWrite) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		workExperience.setId(id);
		workexperienceService.update(workExperience);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		Boolean canWrite = workexperienceService.canWrite(id);
		if (!canWrite) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		workexperienceService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
