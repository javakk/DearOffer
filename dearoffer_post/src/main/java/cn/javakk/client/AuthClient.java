package cn.javakk.client;

import cn.javakk.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: javakk
 * @Date: 2019/5/14 13:58
 */

@FeignClient("dearoffer-auth")
public interface AuthClient {
    /**
     * 远程调用
     * @param ids
     * @return
     */
    @RequestMapping(value = "user/ids/", method= RequestMethod.POST)
    public Result findByIds(List<String> ids);

    /**
     * 远程调用
     * @param id
     * @return
     */
    @RequestMapping(value="user/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id);

}
