package cn.javakk.controller;
import java.util.List;
import java.util.Map;

import cn.javakk.entity.Result;
import cn.javakk.entity.Resume;
import cn.javakk.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.javakk.service.ResumeService;

/**
 * 控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/resume")
public class ResumeController {

	@Autowired
	private ResumeService resumeService;

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true, StatusCode.OK,"查询成功",resumeService.findById(id));
	}

	/**
	 * 增加
	 * @param resume
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Resume resume  ){
		resumeService.add(resume);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param resume
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Resume resume, @PathVariable String id ){
		resume.setId(id);
		resumeService.update(resume);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		resumeService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
