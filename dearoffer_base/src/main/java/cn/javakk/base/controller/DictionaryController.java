package cn.javakk.base.controller;

import cn.javakk.base.entity.Dictionary;
import cn.javakk.base.service.DictionaryService;
import cn.javakk.entity.PageResult;
import cn.javakk.entity.Result;
import cn.javakk.entity.StatusCode;
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
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(dictionaryService.findAll());
    }

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

    /**
     * 增加
     * @param dictionary
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Dictionary dictionary  ){
        dictionaryService.add(dictionary);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param dictionary
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody Dictionary dictionary, @PathVariable String id ){
        dictionary.setId(id);
        dictionaryService.update(dictionary);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        dictionaryService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
