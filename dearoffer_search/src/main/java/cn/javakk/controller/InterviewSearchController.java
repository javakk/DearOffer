package cn.javakk.controller;

import cn.javakk.pojo.*;
import cn.javakk.service.CompanySearchService;
import cn.javakk.service.InterviewSearchService;
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
@RequestMapping("/interview")
public class InterviewSearchController {

    @Autowired
    private InterviewSearchService interviewSearchService;

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
        Page<InterviewExperience> articlePage =
                interviewSearchService.search(keywords, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<InterviewExperience>(articlePage.getTotalElements(), articlePage.getContent()));
    }
}
