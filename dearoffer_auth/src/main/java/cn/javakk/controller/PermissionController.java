package cn.javakk.controller;
import java.util.Map;

import cn.javakk.entity.PageResult;
import cn.javakk.entity.Permission;
import cn.javakk.entity.Result;
import cn.javakk.entity.StatusCode;
import cn.javakk.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(permissionService.findById(id));
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
        Page<Permission> pageList = permissionService.findSearch(searchMap, page, size);
        return  new Result(new PageResult<Permission>(pageList.getTotalElements(), pageList.getContent()));
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",permissionService.findSearch(searchMap));
    }
}
