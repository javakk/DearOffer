package cn.javakk.controller;

import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.pojo.User;
import cn.javakk.service.UserService;
import cn.javakk.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: javakk
 * @Date: 2019/3/24 14:54
 */

@RestController
@CrossOrigin
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Result login(@RequestBody HashMap<String, String> map) {
        Map<String, String> resultMap = new HashMap<>(16);
        if (map != null && !map.isEmpty()) {
            Map searchMap = new HashMap<String, String>(1);
            searchMap.put("phone", map.get("phone"));
            List<User> search = userService.findSearch(searchMap);
            if (search != null && !search.isEmpty()) {
                // 比对密码
                boolean matched = passwordEncoder.matches(map.get("password"), search.get(0).getPassword());
                if (matched) {
                    String token = TokenUtil.createToken(search.get(0).getId(), search.get(0).getUserName(), search.get(0));
                    resultMap.put("userName", search.get(0).getUserName());
                    resultMap.put("token", token);
                    return new Result(true, StatusCode.OK, "登陆成功", resultMap);
                }
            }
        }
        return new Result(false, StatusCode.LOGINERROR, "密码错误");

    }
}
