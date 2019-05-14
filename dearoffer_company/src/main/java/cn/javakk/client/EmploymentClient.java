package cn.javakk.client;

import cn.javakk.pojo.PageResult;
import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: javakk
 * @Date: 2019/5/14 12:44
 */

@FeignClient("dearoffer-employment")
public interface EmploymentClient {


    @PostMapping(value="position/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size);


}
