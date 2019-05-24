package cn.javakk.controller;

import cn.javakk.client.AuthClient;
import cn.javakk.pojo.InterviewExperience;
import cn.javakk.pojo.PageResult;
import cn.javakk.pojo.Result;
import cn.javakk.pojo.StatusCode;
import cn.javakk.service.InterviewExperienceService;
import cn.javakk.util.UserThreadLocal;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 控制器层
 *
 * @author javakk
 */
@RestController
@CrossOrigin
@RequestMapping("/interview")
public class InterviewExperienceController {

    @Autowired
    private InterviewExperienceService interviewExperienceService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AuthClient authClient;

    /**
     * 根据公司id展示
     *
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/company/{companyId}/{page}/{size}", method = RequestMethod.GET)
    public Result findByCompany(@PathVariable String companyId, @PathVariable int page, @PathVariable int size) {
        Map searchMap = new HashMap<String, Object>(1);
        searchMap.put("companyId", companyId);
        Page<InterviewExperience> pageList = interviewExperienceService.findSearch(searchMap, page, size);


        Map resultMap = new HashMap(16);
        resultMap.put("company", new PageResult<InterviewExperience>(pageList.getTotalElements(), pageList.getContent()));


        List userIds = new ArrayList<String>();
        // 用户信息查询
        if (pageList.getContent() != null && pageList.getContent().size() > 0) {
            for (InterviewExperience interviewExperience : pageList.getContent()) {
                String userId = interviewExperience.getPublisherId();
                if (StringUtils.isNotBlank(userId)) {
                    userIds.add(userId);
                } else {
                    userIds.add(UserThreadLocal.getUserId());
                }
            }
        }

        if (userIds != null && userIds.size() > 0) {
            resultMap.put("publisherInfo", authClient.findByIds(userIds).getData());
        }

        return new Result(true, StatusCode.OK, "查询成功", resultMap);
    }


    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {

        Map map = new HashMap(16);
        InterviewExperience interview = interviewExperienceService.findById(id);
        map.put("interview", interview);

        if (interview != null) {
            if (StringUtils.isNotBlank(id)) {
                map.put("publisherInfo", authClient.findById(id).getData());
            } else {
                map.put("publisherInfo", UserThreadLocal.getCrawlerUser());
            }
        }

        return new Result(true, StatusCode.OK, "查询成功", map);
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
        Page<InterviewExperience> pageList = interviewExperienceService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<InterviewExperience>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 增加
     *
     * @param interviewExperience
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody InterviewExperience interviewExperience) {
        // TODO:记录userId
        interviewExperienceService.add(interviewExperience);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 点赞
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable String id) {
        // TODO:userId变更
        String userId = UserThreadLocal.getUserId();
        // Redis键前缀
        String likedKey = "interview:liked:" + id;
        if (redisTemplate.opsForSet().isMember(likedKey, userId)) {
            return new Result(false, StatusCode.ERROR, "您已经赞过");
        }

        interviewExperienceService.updateLikedCount(id);
        redisTemplate.opsForSet().add(likedKey, userId);
        return new Result(true, StatusCode.OK, "点赞成功");

    }

}
