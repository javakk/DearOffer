package cn.javakk.crawler.processor.teachin;

import cn.javakk.entity.TeachIn;
import cn.javakk.crawler.pipeline.MysqlPipeline;
import cn.javakk.crawler.processor.BaseProcessor;
import cn.javakk.util.DateUtil;
import cn.javakk.util.HttpClientDownloader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 宣讲会信息爬取
 * 时间优化:每天采集一次
 *
 * @Author: JavaKK
 * @Date: 2019/4/16 16:40
 */
@Component
public class Job51 implements PageProcessor, BaseProcessor {

    @Autowired
    private RedisTemplate redisTemplate;

    private Integer index = 1;

    private final String LINK = "https://xy.51job.com/xy/xjh.php?provinceid=28&issuedate=&pageno=";

    private Site site = Site
            .me()
            .setDomain("xy.51job.com")
            .setSleepTime(SLEEP_TIME)
            .setRetryTimes(RETRY_TIME)
            .setUserAgent(AGENT);

    @Override
    public void process(Page page) {

        Integer pageTotal = Integer.valueOf(page.getHtml().css("div.p_in>span.td", "text").regex("\\d+").toString());

        List<Selectable> nodes = page.getHtml().css("div.lists>div.e").nodes();


        Boolean onCollect = true;

        List<TeachIn> teachIns = new ArrayList<>();

        ValueOperations opsForValue = redisTemplate.opsForValue();

        for (Selectable node : nodes) {
            String startTime = node.css("div.time", "text").toString();
            if (StringUtils.isNotBlank(startTime)) {
                try {
                    boolean inScale = true;
                    String id = node.css("div.butbox>span", "id").toString();
                    if (inScale && opsForValue.get("crawler:" + id) == null) {

                        opsForValue.set("crawler:" + id, page.getUrl().toString());
                        String cname = node.css("div.cname", "text").toString();
                        String schoolName = node.css("div.area>a", "text").toString();
                        String address = node.css("div.area>span", "text").toString();

                        TeachIn teachIn = new TeachIn();
                        teachIn.setId(UUID.randomUUID().toString());
                        teachIn.setCompanyName(cname);
                        teachIn.setStartTime(startTime);
                        teachIn.setContent("");
                        teachIn.setSchoolName(schoolName);
                        teachIn.setAddress(address);
                        teachIn.setCreateTime(DateUtil.getNow());
                        teachIn.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startTime.substring(1, 11)));
                        teachIn.setSource(page.getUrl().toString());
                        teachIn.setStatus(1);

                        teachIns.add(teachIn);
                    }
                } catch (ParseException e) {
                    System.out.println("===发生异常==>" + e);
                }
            }
        }

        page.putField("teachIn", teachIns);
        teachIns.clear();
        if (++index <= pageTotal && onCollect) {
            page.addTargetRequest(new Request(LINK + index));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) throws ParseException {
        String link = "https://xy.51job.com/xy/xjh.php?provinceid=28&issuedate=&pageno=1";
        Spider.create(new Job51()).setDownloader(new HttpClientDownloader()).addUrl(link)
                .addPipeline(new ConsolePipeline()).thread(THREAD_COUNT).start();
    }
}
