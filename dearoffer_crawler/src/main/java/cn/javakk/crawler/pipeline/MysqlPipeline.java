package cn.javakk.crawler.pipeline;

import cn.javakk.dao.PositionDao;
import cn.javakk.dao.SalaryDao;
import cn.javakk.dao.TeachInDao;
import cn.javakk.entity.Position;
import cn.javakk.entity.Salary;
import cn.javakk.entity.TeachIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @Author: JavaKK
 * @Date: 2019/4/8 9:37
 */

@Service
public class MysqlPipeline implements Pipeline {

    @Autowired
    private SalaryDao salaryDao;
    @Autowired
    private TeachInDao teachInDao;
    @Autowired
    private PositionDao positionDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Salary salary = resultItems.get("salary");
        Position position = resultItems.get("position");
        List<TeachIn> teachIns = resultItems.get("teachIn");

        if(salary != null) {
            salaryDao.save(salary);
        }
        if (position != null) {
            positionDao.save(position);
        }
        if (teachIns != null && teachIns.size() > 0) {
            for (TeachIn teachIn : teachIns) {
                teachInDao.save(teachIn);
            }
        }

    }


}
