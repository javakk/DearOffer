package cn.javakk.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javakk.entity.Comment;
import cn.javakk.entity.PageResult;
import cn.javakk.entity.Result;
import cn.javakk.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.javakk.service.CommentService;

/**
 * 点评控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		Map resultMap = new HashMap<String, Object>(2);
		resultMap.put("parent", commentService.findById(id));
		resultMap.put("subList", commentService.findByParentId(id));
		return new Result(true, StatusCode.OK,"查询成功", resultMap);
	}


	/**
	 * 分页+多条件查询
	 * @param companyId 公司ID
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="{companyId}/{page}/{size}",method=RequestMethod.GET)
	public Result findSearch(@PathVariable String companyId, @PathVariable int page, @PathVariable int size){
		Page<Comment> pageList = commentService.findSearch(companyId, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Comment>(pageList.getTotalElements(), pageList.getContent()) );
	}
	
	/**
	 * 发布评论
	 * @param comment
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Comment comment){
		commentService.add(comment);
		return new Result(true, StatusCode.OK,"发布成功");
	}
	
	/**
	 * 点赞
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@PathVariable String id){
		// TODO:userId变更
		String userId = "000";
		// Redis键前缀
		String likedKey = "comment:liked:" + id;
		if ( redisTemplate.opsForSet().isMember(likedKey, userId)) {
			return new Result(false, StatusCode.ERROR, "您已经赞过");
		}

		commentService.updateLikedCount(id);
		redisTemplate.opsForSet().add(likedKey, userId);
		return new Result(true,StatusCode.OK,"点赞成功");
	}

}
