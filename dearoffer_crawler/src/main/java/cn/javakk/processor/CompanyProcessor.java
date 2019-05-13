package cn.javakk.processor;

import cn.javakk.util.HttpClientDownloader;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Author: JavaKK
 * @Date: 2019/3/20 10:35
 */
@Component
public class CompanyProcessor implements PageProcessor,BaseProcessor {
    /**
     * 开始爬取索引
     */
    private Integer index = 0;
    /**
     * 结束爬取索引
     */
    private final Integer END_INDEX = 500;
    /**
     * 列表页正则匹配
     */
    private final String PAGE_REGEX = "https://job.csdn.net/enterpriselibrary/list\\?keyWord=&pageIndex=\\d+";


    private Site site = Site
            .me()
            .setDomain("csdn.net")
            .setSleepTime(SLEEP_TIME)
            .setRetryTimes(RETRY_TIME)
            .setUserAgent(AGENT);

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(PAGE_REGEX).match()) {
            // 爬取列表页
            index++;
            page.addTargetRequests(page.getHtml().xpath("//dl[@class=\"about_company\"]/dt").links().all());

            page.addTargetRequest("https://job.csdn.net/enterpriselibrary/list?keyWord=&pageIndex=" + index);
            if (index.equals(END_INDEX)) {
                return;
            }
        } else {
            // 抽取详情页
            page.putField("com_name", page.getHtml().xpath("//dd[@class=\"com_name set_icon\"]//a/text()"));
            page.putField("com_tags", page.getHtml().xpath("//dd[@class=\"com_tags\"]/span/text()").all());
            page.putField("com_detail", page.getHtml().xpath("//dd[@class=\"com_detail\"]/a/text()"));
            page.putField("com_website", page.getHtml().xpath("//dd[@class=\"com_website\"]/a/text()"));
            page.putField("com_logo_src", page.getHtml().xpath("//dt[@class=\"com_logo\"]/a").css("img", "src"));
            page.putField("com_position", page.getHtml().xpath("//div[@class=\"position\"]/text()"));
            page.putField("com_introduction", page.getHtml().xpath("//div[@class=\"company_detail\"]/text()"));
            page.putField("com_images", page.getHtml().xpath("//ul[@class=\"slide_list\"]/li/a").css("img", "src").all());

            page.putField("welfare_list", page.getHtml().xpath("//ul[@class=\"welfare_list\"]/li/text()").all());
            page.putField("welfare_scale", page.getHtml().xpath("//div[@class=\"salary\"]/em/text()"));


            page.putField("job_names", page.getHtml().xpath("//dl[@class=\"job_list\"]/dt/a/text()").all());
            page.putField("job_experiences", page.getHtml().xpath("//dl[@class=\"job_list\"]/dd[@class=\"experience\"]/text()").all());
            page.putField("job_sites", page.getHtml().xpath("//dl[@class=\"job_list\"]/dd[@class=\"job_site\"]/text()").all());
            page.putField("job_salary_scales", page.getHtml().xpath("//dl[@class=\"job_list\"]/dd[@class=\"job_salary\"]/text()").all());

        }


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main (String[] args) {
        String startUrl = "https://job.csdn.net/enterpriselibrary/list?keyWord=&pageIndex=1";
        Spider.create(new CompanyProcessor()).setDownloader(new HttpClientDownloader()).addUrl(startUrl)
                .addPipeline(new ConsolePipeline()).thread(THREAD_COUNT).start();
    }
}