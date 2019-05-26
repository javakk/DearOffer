package cn.javakk.crawler.processor.company;

import cn.javakk.entity.Company;
import cn.javakk.crawler.pipeline.CompanyPipeline;
import cn.javakk.crawler.processor.BaseProcessor;
import cn.javakk.util.HttpClientDownloader;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

/**
 * @Author: JavaKK
 * @Date: 2019/4/28 10:12
 */
public class Boss implements PageProcessor, BaseProcessor {

    private static final String DOMAIN = "https://www.zhipin.com";

    private static final String LIST_KEY = "page";


    private static String listUrlPre = "https://www.zhipin.com/gongsi/_zzz_t80";
    private static int typeIndex = 4;
    private static String listUrlPage = "/?page=";
    private static int index = 5;
    private static String listUrlEnd = "&expectId=cb1494e0c1011eccynU~";

    private static String cityUrl = "https://www.zhipin.com/gongsi/_zzz_c101270100/?page=";

    private static String cityUrlEnd = "&expectId=cb1494e0c1011eccynU~";

    private Site site = Site
            .me()
            .setDomain(DOMAIN)
            .setSleepTime(SLEEP_TIME)
            .setRetryTimes(RETRY_TIME)
            .setUserAgent(AGENT);

    @Override
    public void process(Page page) {

        if (page.getUrl().toString().contains(LIST_KEY)) {
            List<Selectable> nodes = page.getHtml().css("div.company-list>ul>li>div.sub-li>a:first-child", "href").nodes();

            if (nodes == null || nodes.size() == 0) {
                page.setSkip(true);
            }

            for (Selectable node : nodes) {
                String s = node.get();
                page.addTargetRequest(DOMAIN + s);
            }
            ++index;
            if (index == 10 && typeIndex < 9) {
                ++typeIndex;
                index = 1;
            } else if (index == 10) {
                return;
            }
            page.addTargetRequest(cityUrl + index + cityUrlEnd);
        } else {

            String logo = page.getHtml().css("div.info-primary>img", "src").get();
            String shortName = page.getHtml().css("div.info>h1", "text").get();

            page.putField("logo", logo);
            page.putField("shortName", shortName);


            String financingTag = "";
            String companyScaleTag = "";
            String industryTag = "";

            String baseInfo = page.getHtml().css("div.info>p").get();
            if (StringUtils.isNotBlank(baseInfo)) {
                String[] splits = baseInfo.split("<em class=\"dolt\"></em>");
                financingTag = splits[0].substring(3);
                companyScaleTag = splits[1];
                industryTag = splits[2].substring(0, splits[2].length() - 4);
            }

            List<String> allTags = page.getHtml().css("div.tag-container>div.job-tags>span", "text").all();
            Set tagSet = new HashSet<String>();
            for (String tag : allTags) {
                tagSet.add(tag);
            }

            String tagString = tagSet.toString();

            String welfareTag = tagString.substring(1, tagString.length() - 1);

            String introduction = page.getHtml().css("div.job-sec>div.fold-text", "text").get();
            String name = page.getHtml().css("div.job-sec>h4", "text").get();


            List<String> businessDetails = page.getHtml().css("div.business-detail>ul>li", "text").all();
            String registerPerson = "";
            String registerMoney = "";
            String registerTime = "";
            String companyType = "";
            String companyStatus = "";
            String registerAddress = "";
            String registerCode = "";
            String sellScale = "";
            // 完整的工商信息
            try {
                registerPerson = businessDetails.get(0);
                registerMoney = businessDetails.get(1);
                registerTime = businessDetails.get(2);
                companyType = businessDetails.get(3);
                companyStatus = businessDetails.get(4);
                registerAddress = businessDetails.get(5);
                registerCode = businessDetails.get(6);
                sellScale = businessDetails.get(7);
            } catch (Exception e) {

            }


            String[] split = page.getUrl().toString().split("/");
            String companyId = split[4].substring(0, split[4].length() - 5);


            Company company = new Company(logo, shortName, financingTag, companyScaleTag, industryTag, welfareTag, introduction, name, registerPerson, registerMoney, registerTime, companyType, companyStatus, registerAddress, registerCode, sellScale, new Date(), page.getUrl().toString(), companyId);

            page.putField("company", company);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        int index = 1;
        String url = "https://www.zhipin.com/gongsi/?page=" + index + "&expectId=cb1494e0c1011eccynU~";
        Request request = new Request(url);
        request.addHeader("Cookie", "t=VhGkVMhOkhLAvZs; wt=VhGkVMhOkhLAvZs; __c=1556414153; __g=-; __l=l=%2Fwww.zhipin.com%2F&r=; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1556158306,1556159048,1556240438,1556414153; JSESSIONID=\"\"; __a=62187951.1556158304.1556240438.1556414153.31.3.25.31; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1556417592");
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        Spider.create(new Boss()).setDownloader(new HttpClientDownloader()).addUrl(url)
                .addPipeline(new CompanyPipeline()).thread(THREAD_COUNT).start();

    }
}
