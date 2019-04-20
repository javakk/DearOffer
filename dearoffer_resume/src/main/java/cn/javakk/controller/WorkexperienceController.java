package cn.javakk.controller;
import java.util.Map;

import cn.javakk.entity.Result;
import cn.javakk.entity.StatusCode;
import cn.javakk.entity.WorkExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.javakk.service.WorkExperienceService;

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
