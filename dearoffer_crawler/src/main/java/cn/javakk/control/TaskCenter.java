package cn.javakk.control;

import cn.javakk.crawler.pipeline.CompanyPipeline;
import cn.javakk.crawler.pipeline.MysqlPipeline;
import cn.javakk.crawler.processor.position.Uestc;
import cn.javakk.crawler.processor.salary.SalaryProcessor;
import cn.javakk.crawler.processor.teachin.Job51;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JavaKK
 * @Date: 2019/4/28 14:42
 */
@Component
@EnableScheduling
public class TaskCenter {

    @Autowired
    private CompanyPipeline companyPipeline;

    @Autowired
    private MysqlPipeline mysqlPipeline;

    @Autowired
    private Uestc uestc;

    @Transactional
    @Scheduled(cron = "30 38 13 * * ?")
    public void createSalaryInfo(){
        System.out.println("===工资信息爬虫开始执行===");

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
    @Scheduled(cron = "59 15 14 * * ?")
    public void createTeachInInfo(){
        System.out.println("===开始执行");

        String url = "https://xy.51job.com/xy/xjh.php?provinceid=28&issuedate=&pageno=1";
        Request request = new Request(url);

        try {
            Spider spider = Spider.create(new Job51());
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
    @Scheduled(cron = "40 03 16 * * ?")
    public void createPositionInfo(){
        System.out.println("===职位信息爬虫开始执行===");

        String link = "http://jiuye.uestc.edu.cn/sys/fore.php?op=listRecruit";

        Request request = new Request(link);
        request.setMethod("post");
        Map map = new HashMap<>(16);
        map.put("rec_way", 2);
        map.put("page", 1);
        request.setRequestBody(HttpRequestBody.form(map, "utf-8"));

        try {
            Spider spider = Spider.create(uestc);
            spider.addRequest(request);
            spider.addPipeline(mysqlPipeline);
            spider.thread(8);
            spider.setExitWhenComplete(true);
            spider.start();
            spider.stop();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
