package cn.javakk.crawler.processor.interview;

import cn.javakk.crawler.pipeline.MysqlPipeline;
import cn.javakk.crawler.processor.BaseProcessor;
import cn.javakk.entity.InterviewExperience;
import cn.javakk.util.DateUtil;
import cn.javakk.util.HttpClientDownloader;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.*;

/**
 * @Author: JavaKK
 * @Date: 2019/4/18 10:30
 */
@Component
public class NewCoder implements PageProcessor, BaseProcessor {

    /**
     * type:2 -> 经验
     * order:3 -> 最新发布
     */
    private String LIST_LINK = "https://www.nowcoder.com/discuss/tag/639?type=2&order=3&pageSize=30&query=&page=";

    private final String LIST_FIX = "query";

    private final String DOMAIN = "https://www.nowcoder.com/";

    /**
     * 当前采集页码
     */
    private Integer index = 1;

    private Site site = Site
            .me()
            .setDomain("nowcoder.com")
            .setSleepTime(SLEEP_TIME)
            .setRetryTimes(RETRY_TIME)
            .setUserAgent(AGENT);

    @Override
    public void process(Page page) {
        if (page.getUrl().toString().contains(LIST_FIX)) {
            List<String> links = page.getHtml().css("div.discuss-main>a:nth-child(1)", "href").all();

            List<String> createTimes = page.getHtml().css("p.feed-tip", "text").regex("\\d+[: -]\\d+[: -]\\d+").all();

            if (links != null && !links.isEmpty()) {
                for (String link : links) {
                    // 添加详情页采集任务
                    page.addTargetRequest(new Request(DOMAIN + link));
                }
            }

            Integer pageCount = Integer.valueOf(page.getHtml().css("li.js-last-pager>a", "data-page").get());

            if (++index <= pageCount) {
                // 添加下一页采集任务
                page.addTargetRequest(new Request(LIST_LINK + index));
            }
        } else {


            List<String> content = page.getHtml().css("div.post-topic-main>div.post-topic-des *").all();

            if (content.isEmpty() || content.toString().matches("(<br>)+")) {
                content = page.getHtml().css("div.post-topic-main>div.post-topic-des", "text").all();
            }

            StringBuffer buffer = new StringBuffer();
            for (String temp : content) {
                buffer.append(temp.replaceAll("牛客", "DearOffer"));
            }


            InterviewExperience experience = new InterviewExperience();


            experience.setId(UUID.randomUUID().toString());
            experience.setStatus(1);
            experience.setSource(page.getUrl().toString());
            experience.setCreateTime(DateUtil.getNow());

            experience.setCompanyName(page.getHtml().css("a.tag-label", "text").get());
            experience.setTitle(page.getHtml().css("h1.discuss-title", "text").get());
            experience.setPublisherHead(page.getHtml().css("a.answer-head>img", "src").get());
            experience.setPublisherName(page.getHtml().css("a.post-name", "text").get());
            experience.setPublisherDate(page.getHtml().css("span.post-time", "text").get());
            experience.setLikeCount((long) new Random().nextInt(1000));
            experience.setScore(new Random().nextInt(6));
            experience.setContent(new String(buffer));


            page.putField("interview", experience);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String link = "https://www.nowcoder.com/discuss/tag/639?type=2&order=3&pageSize=30&query=&page=1";
        Spider.create(new NewCoder()).setDownloader(new HttpClientDownloader()).addUrl(link)
                .addPipeline(new MysqlPipeline()).thread(THREAD_COUNT).start();

    }
}
