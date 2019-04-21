package cn.javakk.controller;

import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.pojo.User;
import cn.javakk.service.UserService;
import cn.javakk.util.RandomUtil;
import io.jsonwebtoken.Claims;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 控制器层
 * @author javakk
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private HttpServletRequest request;

	@Value("${message.register.effectiveTime}")
	private String registerEffectiveTime;


	@Value("${redis.keys.register_code_fix}")
	private String registerKeyFix;

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(userService.findById(id));
	}

	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id){
		Map loginUser = (Map) request.getAttribute("user");
		if (loginUser == null || !loginUser.get("id").equals(id)) {
			return new Result(false, StatusCode.ACCESSERROR, "权限不足");
		}
		user.setId(id);
		userService.update(user);		
		return new Result(true, StatusCode.OK,"修改成功");
	}

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@PostMapping(value="/{code}")
	public Result add(@RequestBody User user, @PathVariable String code) {

		String redisCode = (String) redisTemplate.opsForValue().get(registerKeyFix + user.getPhone());
		if (StringUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
			return new Result(false, StatusCode.ERROR, "验证码错误");
		}
		userService.add(user);
		return new Result(true, StatusCode.OK, "成功");
	}

	/**
	 * 获取验证码
	 * @param phone
	 * @return
	 */
	@GetMapping(value = "/code/{phone}")
	public Result getCode( @PathVariable String phone){
        // TODO:IP限制
        Map searchMap = new HashMap<String, String>(1);
        searchMap.put("phone", phone);
        List<User> searchedUser = userService.findSearch(searchMap);

        if (searchedUser != null && !searchedUser.isEmpty()) {
            return new Result(false, StatusCode.ERROR, "手机号码已经注册");
        }

        if (redisTemplate.opsForValue().get(registerKeyFix + phone) != null) {
			return new Result(false, StatusCode.ERROR, "操作太过频繁，稍后重试");
		}

		String code = RandomUtil.generateValidCode();
		redisTemplate.opsForValue().set(registerKeyFix + phone, code);
		redisTemplate.expire(registerKeyFix + phone, Long.parseLong(registerEffectiveTime), TimeUnit.MINUTES);

		Map messageMap = new HashMap<String, String>(2);
		messageMap.put("mobile", phone);
		messageMap.put("code", code);

		rabbitTemplate.convertAndSend("register", messageMap);
		return new Result(true, StatusCode.OK, "获取成功", code);
	}
}
