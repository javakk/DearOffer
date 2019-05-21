package cn.javakk.controller;

import cn.javakk.pojo.ProjectExperience;
import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.javakk.service.ProjectExperienceService;

/**
 * 控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/projectExperience")
public class ProjectExperienceController {

	@Autowired
	private ProjectExperienceService projectexperienceService;

	/**
	 * 增加
	 * @param projectexperience
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody ProjectExperience projectexperience  ){
		projectexperienceService.add(projectexperience);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param projectexperience
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody ProjectExperience projectexperience, @PathVariable String id ){
		Boolean canWrite = projectexperienceService.canWrite(id);
		if (!canWrite) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		projectexperience.setId(id);
		projectexperienceService.update(projectexperience);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		Boolean canWrite = projectexperienceService.canWrite(id);
		if (!canWrite) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		projectexperienceService.deleteById(id);
		return new Result(true, StatusCode.OK,"删除成功");
	}
	
}
