package cn.javakk.processor.interview;

import cn.javakk.pipeline.CompanyPipeline;
import cn.javakk.processor.BaseProcessor;
import cn.javakk.util.HttpClientDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: JavaKK
 * @Date: 2019/4/18 10:30
 */
public class NewCoder implements PageProcessor, BaseProcessor {

    /**
     * type:2 -> 经验
     * order:3 -> 最新发布
     */
    private String LIST_LINK = "https://www.nowcoder.com/discuss/tag/138?type=2&order=3&pageSize=30&query=&page=";

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

            Map collectLink = new HashMap<String, Boolean>(30);

            collectPreprocess(collectLink, links, createTimes);

            if (links != null && !links.isEmpty()) {
                for (String link : links) {
                    // 添加详情页采集任务
                    if ((Boolean) collectLink.get(link)) {
                        page.addTargetRequest(new Request(DOMAIN + link));
                    }
                }
            }

            Integer pageCount = Integer.valueOf(page.getHtml().css("li.js-last-pager>a", "data-page").get());

            if (++index <= pageCount) {
                // 添加下一页采集任务
                page.addTargetRequest(new Request(LIST_LINK + index));
            }
        } else {
            // 标签采集:相当于公司的ID
            List<String> tags = page.getHtml().css("a.tag-label", "href").regex("\\w+\\d+").all();
            // 忽略没有标签的贴
            if (tags == null || tags.isEmpty()) {
                page.setSkip(true);
            }

            List<String> content = page.getHtml().css("div.post-topic-main>div.post-topic-des *").all();

            if (content.isEmpty() || content.toString().matches("(<br>)+")) {
                content = page.getHtml().css("div.post-topic-main>div.post-topic-des", "text").all();
            }

            StringBuffer buffer = new StringBuffer();
            for (String temp : content) {
                buffer.append(temp.replaceAll("牛客", "DearOffer"));
            }

            page.putField("tags", tags);
            page.putField("content", buffer);
            page.putField("url", page.getUrl().toString());
        }
    }

    /**
     * 采集预处理:增量采集、全量采集
     * @param collectLink
     * @param links
     * @param createTimes
     */
    private void collectPreprocess(Map collectLink, List<String> links, List<String> createTimes) {
        if (!fullCollect) {
            // 增量采集
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < links.size(); i++) {
                String createTime = createTimes.get(i);
                String link = links.get(i);
                Date parsedTime = null;
                try {
                    if (createTime.contains(":")) {
                        parsedTime = timeFormat.parse(createTime);
                    } else {
                        parsedTime = dateFormat.parse(createTime);
                    }
                } catch (Exception e) {
                    // Catch Date Error
                }
                collectLink.put(link, parsedTime.getTime() > lastCollectTime.getTime());
            }
        } else {
            // 全量采集
            for (String link : links) {
                collectLink.put(link, true);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String link = "https://www.nowcoder.com/discuss/tag/138?type=2&order=3&pageSize=30&query=&page=1";
        Spider.create(new NewCoder()).setDownloader(new HttpClientDownloader()).addUrl(link)
                .addPipeline(new CompanyPipeline()).thread(THREAD_COUNT).start();

    }
}
