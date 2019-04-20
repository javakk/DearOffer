package cn.javakk.controller;

import cn.javakk.pojo.*;
import cn.javakk.service.CompanySearchService;
import cn.javakk.service.InterviewSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(value="/search/{keywords}/{page}/{size}")
    public Result findByTitleLike(@PathVariable String keywords,
                                  @PathVariable int page,
                                  @PathVariable int size){
        Page<InterviewExperience> articlePage =
                interviewSearchService.search(keywords, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<InterviewExperience>(articlePage.getTotalElements(), articlePage.getContent()));
    }
}
