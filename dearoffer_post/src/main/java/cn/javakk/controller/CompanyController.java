package cn.javakk.controller;

import cn.javakk.client.PostClient;
import cn.javakk.pojo.Company;
import cn.javakk.pojo.PageResult;
import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器层
 *
 * @author javakk
 */
@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PostClient postClient;

    @Autowired
    private EmploymentClient employmentClient;

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        Map resultMap = new HashMap(4);
        Company company = companyService.findById(id);
        resultMap.put("company", company);

        // 点评信息
        Result commentResult = postClient.findSearch(id, 1, 2);
        resultMap.put("comment", commentResult.getData());

        // 查询关联职位信息
        Map positionSearchMap = new HashMap(4);
        positionSearchMap.put("companyId", id);
        Result positionResult = employmentClient.findSearch(positionSearchMap, 1, 2);
        resultMap.put("position", positionResult.getData());

        return new Result(true, StatusCode.OK, "查询成功", resultMap);
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Company> pageList = companyService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Company>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", companyService.findSearch(searchMap));
    }
}
