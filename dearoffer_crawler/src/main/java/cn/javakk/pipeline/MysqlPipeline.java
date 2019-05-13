package cn.javakk.pipeline;

import cn.javakk.dao.SalaryDao;
import cn.javakk.dao.TeachInDao;
import cn.javakk.entity.Salary;
import cn.javakk.entity.TeachIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

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

    @Override
    public void process(ResultItems resultItems, Task task) {

        Salary salary = resultItems.get("salary");

        TeachIn teachIn = resultItems.get("teachIn");

        if (salary == null && teachIn == null) {
            return;
        }

        if(salary != null) {
            salaryDao.save(salary);
        }
        if (teachIn != null) {
            teachInDao.save(teachIn);
        }


    }


}
