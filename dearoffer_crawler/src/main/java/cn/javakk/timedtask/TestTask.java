package cn.javakk.timedtask;

import cn.javakk.pipeline.CompanyPipeline;
import cn.javakk.pipeline.MysqlPipeline;
import cn.javakk.processor.company.Boss;
import cn.javakk.processor.salary.SalaryProcessor;
import cn.javakk.processor.teachin.Job51Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * @Author: JavaKK
 * @Date: 2019/4/28 14:42
 */
@Component
@EnableScheduling
public class TestTask {

    @Autowired
    private CompanyPipeline companyPipeline;

    @Autowired
    private MysqlPipeline mysqlPipeline;

    @Transactional
    @Scheduled(cron = "30 38 13 * * ?")
    public void createSalaryInfo(){
        System.out.println("===开始执行");

        String url = "https://offernews.cn/?pageIndex=16";
        Request request = new Request(url);

        try {
            Spider spider = Spider.create(new SalaryProcessor());
            spider.addRequest(request);
            spider.addPipeline(mysqlPipeline);
            spider.thread(5);
            spider.setExitWhenComplete(true);
            spider.start();
            spider.stop();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Transactional
    @Scheduled(cron = "59 00 14 * * ?")
    public void createTeachInInfo(){
        System.out.println("===开始执行");

        String url = "https://xy.51job.com/xy/xjh.php?provinceid=28&issuedate=&pageno=1";
        Request request = new Request(url);

        try {
            Spider spider = Spider.create(new Job51Processor());
            spider.addRequest(request);
            spider.addPipeline(mysqlPipeline);
            spider.thread(5);
            spider.setExitWhenComplete(true);
            spider.start();
            spider.stop();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
