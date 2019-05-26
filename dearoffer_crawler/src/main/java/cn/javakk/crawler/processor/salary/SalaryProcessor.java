package cn.javakk.crawler.processor.salary;

import cn.javakk.entity.Salary;
import cn.javakk.crawler.pipeline.MysqlPipeline;
import cn.javakk.crawler.processor.BaseProcessor;
import cn.javakk.util.DateUtil;
import cn.javakk.util.HttpClientDownloader;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

/**
 * 工资信息
 * 时间优化:每天采集一次
 * @Author: JavaKK
 * @Date: 2019/4/16 16:40
 */
public class SalaryProcessor implements PageProcessor, BaseProcessor {

    @Autowired
    private IdWorker idWorker;

    private Integer index = 16;

    private Integer endIndex = 20;

    private final String LINK = "https://offernews.cn/?pageIndex=";

    private Site site = Site
            .me()
            .setDomain("offernews.cn")
            .setSleepTime(SLEEP_TIME)
            .setRetryTimes(RETRY_TIME)
            .setUserAgent(AGENT);

    @Override
    public void process(Page page) {

        if (page.getUrl().toString().contains("pageIndex")) {
            List<String> links = page.getHtml().css("table.table>tbody>tr>td:nth-child(5)>a", "href").all();

            for (String link : links) {
                page.addTargetRequest(new Request("https://offernews.cn/" + link));
            }

            if (++index <= endIndex) {
                page.addTargetRequest(new Request(LINK + index));
            }

        } else {
            String companyName = page.getHtml().css("div.jumbotron>div>h2", "text").get().replace("的offer爆料", "");
            String city = page.getHtml().css("div.jumbotron>div>table>tbody>tr:nth-child(1)>td:nth-child(2)", "text").get();
            String position = page.getHtml().css("div.jumbotron>div>table>tbody>tr:nth-child(2)>td:nth-child(2)", "text").get();
            String salary = page.getHtml().css("div.jumbotron>div>table>tbody>tr:nth-child(3)>td:nth-child(2)>span", "text").get();
            String other = page.getHtml().css("div.jumbotron>div>table>tbody>tr:nth-child(4)>td:nth-child(2)", "text").get();
            String getTime =page.getHtml().css("div.jumbotron>div>table>tbody>tr:nth-child(5)>td:nth-child(2)", "text").get();

            List<String> tags = page.getHtml().css("span.badge", "text").all();

            int pageView = Integer.parseInt(tags.get(0));
            int credibility = Integer.parseInt(tags.get(1)) + Integer.parseInt(tags.get(2));

            Salary salaryPojo = new Salary();
            salaryPojo.setId(UUID.randomUUID().toString());
            salaryPojo.setCredibilityPercent((float) credibility);
            salaryPojo.setPageView((long) pageView);
            salaryPojo.setCompanyName(companyName);
            salaryPojo.setCity(city);
            salaryPojo.setPositionTitle(position);
            salaryPojo.setBonus(salary);
            salaryPojo.setDetail(other);
            salaryPojo.setCredibility(credibility);
            salaryPojo.setCreateTime(DateUtil.getNow());
            salaryPojo.setStatus(1);

            page.putField("salary", salaryPojo);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) throws ParseException {
        String link = "https://offernews.cn/?pageIndex=16";
        Spider.create(new SalaryProcessor()).setDownloader(new HttpClientDownloader()).addUrl(link)
                .addPipeline(new MysqlPipeline()).thread(THREAD_COUNT).start();
    }
}
