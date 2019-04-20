package cn.javakk.controller;
import java.util.List;
import java.util.Map;

import cn.javakk.entity.Result;
import cn.javakk.entity.Resume;
import cn.javakk.entity.StatusCode;
import cn.javakk.util.UserThreadLocal;
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
 * 简历控制器层
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
	 * 查询我所有简历
	 * @return
	 */
	@RequestMapping(value="",method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",resumeService.findAll());
	}

	/**
	 * 聚合简历详情
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		Boolean canRead = resumeService.canRead(id);
		if (!canRead) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		return new Result(true, StatusCode.OK,"查询成功",resumeService.findById(id));
	}

	/**
	 * 新创建简历
	 * @param resume
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Resume resume){
		String userId = UserThreadLocal.getUserId();
		resume.setPublisherId(userId);
		resumeService.add(resume);
		return new Result(true,StatusCode.OK,"创建简历成功");
	}
	
	/**
	 * 修改
	 * @param resume
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Resume resume, @PathVariable String id ){
		if (!resumeService.canWrite(id)) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
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
		Boolean canWrite = resumeService.canWrite(id);
		if (!canWrite) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		resumeService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
