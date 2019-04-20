package cn.javakk.controller;

import cn.javakk.pojo.Dictionary;
import cn.javakk.service.DictionaryService;
import cn.javakk.pojo.PageResult;
import cn.javakk.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: javakk
 * @Date: 2019/3/23 17:36
 */

@RestController
@CrossOrigin
@RequestMapping("/dic")
public class DictionaryController {


    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(dictionaryService.findById(id));
    }


    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Dictionary> pageList = dictionaryService.findSearch(searchMap, page, size);
        return  new Result(new PageResult<Dictionary>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(dictionaryService.findSearch(searchMap));
    }

}
