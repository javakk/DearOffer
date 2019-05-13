package cn.javakk.processor.teachin;

import cn.javakk.entity.TeachIn;
import cn.javakk.pipeline.CompanyPipeline;
import cn.javakk.pipeline.MysqlPipeline;
import cn.javakk.processor.BaseProcessor;
import cn.javakk.util.DateUtil;
import cn.javakk.util.HttpClientDownloader;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class Job51Processor implements PageProcessor, BaseProcessor {

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

        for (Selectable node : nodes) {
            String startTime = node.css("div.time", "text").toString();
            if (StringUtils.isNotBlank(startTime)) {
                String temp = startTime.replaceAll("\\W", "");
                String regex = "yyyyMMdd";
                SimpleDateFormat dateFormat = new SimpleDateFormat(regex);
                try {
                    Date createTime = dateFormat.parse(temp.substring(0, 8));
                    long restDay = (createTime.getTime() - lastCollectTime.getTime()) / 1000 / 60 / 60 / 24;
//                    boolean inScale = restDay >= 0 && restDay <= 21;
                    boolean inScale = true;
                    if (inScale) {
                        // 距离上次采集2周内的数据
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
                        teachIn.setStatus(1);

                        page.putField("teachIn", teachIn);
                    } else if (restDay < 0) {
                        onCollect = false;
                        break;
                    }
                } catch (ParseException e) {
                    // Parse Date Error
                }
            }
        }
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
        Spider.create(new Job51Processor()).setDownloader(new HttpClientDownloader()).addUrl(link)
                .addPipeline(new MysqlPipeline()).thread(THREAD_COUNT).start();
    }
}
