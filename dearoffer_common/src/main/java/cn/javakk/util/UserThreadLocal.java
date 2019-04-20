package cn.javakk.util;

import cn.javakk.entity.User;

import java.util.Date;

/**
 * 临时用来获取请求用户的工具
 * @Author: javakk
 * @Date: 2019/4/19 23:57
 */
public class UserThreadLocal {

    public static String getUserId() {
        return "0001";
    }


    public static User getUser() {
        User user = new User();
        user.setId("0001");
        user.setCreateTime(new Date());
        user.setEmail("1037441463@qq.com");
        user.setHeadImage("https://img.52z.com/upload/news/image/20180213/20180213062641_35687.jpg");
        user.setPassword("password");
        user.setPhone("17828095956");
        user.setSex(1);
        user.setStatus(1);
        user.setUserName("JavaKK");
        user.setRoleId("roleId");
        return user;
    }
}
