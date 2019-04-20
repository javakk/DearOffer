package cn.javakk.controller;

import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.service.CompanySearchService;
import cn.javakk.service.InterviewSearchService;
import cn.javakk.service.PositionSearchService;
import cn.javakk.service.SalarySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 聚合查询
 * @Author: javakk
 * @Date: 2019/4/20 22:37
 */
@RestController
@CrossOrigin
@RequestMapping("/aggregation")
public class AggregationSearchController {

    @Autowired
    private CompanySearchService companySearchService;
    @Autowired
    private InterviewSearchService interviewSearchService;
    @Autowired
    private PositionSearchService positionSearchService;
    @Autowired
    private SalarySearchService salarySearchService;

    @Value("${const.index-search.pageSize}")
    private Integer pageSize;

    @GetMapping(value = "/search/{keyword}")
    public Result search(@PathVariable String keyword) {
        Map resultMap = new HashMap<String, Object>(4);
        resultMap.put("company", companySearchService.search(keyword, 1, pageSize).getContent());
        resultMap.put("interview", interviewSearchService.search(keyword, 1, pageSize).getContent());
        resultMap.put("position", positionSearchService.search(keyword, 1, pageSize).getContent());
        resultMap.put("salary", salarySearchService.search(keyword, 1, pageSize).getContent());
        return new Result(true, StatusCode.OK, "搜索成功", resultMap);
    }
}
