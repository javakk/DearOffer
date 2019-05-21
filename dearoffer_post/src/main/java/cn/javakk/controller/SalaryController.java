package cn.javakk.controller;

import cn.javakk.pojo.PageResult;
import cn.javakk.pojo.Result;
import cn.javakk.pojo.Salary;
import cn.javakk.pojo.StatusCode;
import cn.javakk.service.SalaryService;
import cn.javakk.crawler.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/salary")
public class SalaryController {

	@Autowired
	private SalaryService salaryService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Value("${redis.keys.salary_credibility_fix}")
	private String credibilityKeyFix;

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true, StatusCode.OK,"查询成功",salaryService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Salary> pageList = salaryService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Salary>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",salaryService.findSearch(searchMap));
    }
	
	/**
	 * 发布
	 * @param salary
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Salary salary  ){
		salaryService.add(salary);
		return new Result(true,StatusCode.OK,"发布成功");
	}

	/**
	 * 可信度修改
	 * @param id
	 * @param agree
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.POST)
	public Result update(@PathVariable String id, Boolean agree){
		String userId = UserThreadLocal.getUserId();
		if (redisTemplate.opsForSet().isMember(credibilityKeyFix + id, userId)) {
			return new Result(false, StatusCode.OK, "已经回馈过");
		}
		salaryService.updateCredibility(id, agree);
		redisTemplate.opsForSet().add(credibilityKeyFix + id, userId);
		return new Result(true,StatusCode.OK,"操作成功");
	}
}
