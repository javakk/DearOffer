package cn.javakk.client;

import cn.javakk.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: javakk
 * @Date: 2019/5/14 12:44
 */

@FeignClient("dearoffer-post")
public interface PostClient {

    @RequestMapping(value = "/comment/{companyId}/{page}/{size}", method = RequestMethod.GET)
    public Result findSearch(@PathVariable String companyId, @PathVariable int page, @PathVariable int size);
}
