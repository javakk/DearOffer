package cn.javakk.processor.common;

import cn.javakk.pipeline.CompanyPipeline;
import cn.javakk.processor.BaseProcessor;
import cn.javakk.util.HttpClientDownloader;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.Random;

/**
 * @Author: JavaKK
 * @Date: 2019/4/18 10:30
 */
public class NewCoderTag implements PageProcessor, BaseProcessor {

    private String LIST_LINK = "https://www.nowcoder.com/discuss/tags?type=0";

    private final String DOMAIN = "https://www.nowcoder.com/";



    private Site site = Site
            .me()
            .setDomain("nowcoder.com")
            .setSleepTime(SLEEP_TIME)
            .setRetryTimes(RETRY_TIME)
            .setUserAgent(AGENT);

    @Override
    public void process(Page page) {
        List<Selectable> tagNodes = page.getHtml().css("div.tags-wrapper").nodes();

        List<Selectable> tagItems = tagNodes.get(tagNodes.size() - 1).css("ul.discuss-tags-mod>li").nodes();

        for (Selectable tagItem : tagItems) {
            String tagId = tagItem.css("li>a", "href").regex("\\d+").get();
            String tagName = tagItem.css("li>a>span", "text").get();
            String count = tagItem.css("li>a>span:nth-child(2)", "text").regex("\\d+").get();
            if (StringUtils.isBlank(count) || "0".equals(count)) {
                continue;
            }
            page.putField(tagId, tagId);
            page.putField(tagName, tagName);
            page.putField(tagName + "-", count);
            page.putField(tagName + "=", "========================");
        }
    }



    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String link = "https://www.nowcoder.com/discuss/tags?type=0";
        Spider.create(new NewCoderTag()).setDownloader(new HttpClientDownloader()).addUrl(link)
                .addPipeline(new ConsolePipeline()).thread(THREAD_COUNT).start();

    }
}
