package cn.javakk.controller;

import cn.javakk.pojo.Company;
import cn.javakk.pojo.PageResult;
import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.service.CompanySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: javakk
 * @Date: 2019/4/20 20:58
 */
@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanySearchController {

    @Autowired
    private CompanySearchService companySearchService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${redis.keys.search}")
    private String searchKey;

    @GetMapping(value="/search/{keywords}/{page}/{size}")
    public Result findByTitleLike(@PathVariable String keywords,
                                  @PathVariable int page,
                                  @PathVariable int size){
        ZSetOperations opsForZSet = redisTemplate.opsForZSet();
        opsForZSet.incrementScore(searchKey, keywords, 1);

        Page<Company> articlePage = companySearchService.search(keywords, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Company>(articlePage.getTotalElements(), articlePage.getContent()));
    }
}
