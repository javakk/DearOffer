package cn.javakk.processor.campus;

import cn.javakk.pipeline.CompanyPipeline;
import cn.javakk.processor.BaseProcessor;
import cn.javakk.util.HttpClientDownloader;
import org.apache.commons.collections.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 成电信息爬取
 * @Author: JavaKK
 * @Date: 2019/4/8 10:18
 */
public class Uestc implements PageProcessor, BaseProcessor {

    private final String ALL_KEY = "op=listRecruit";
    private final String TODAY_KEY = "op=searchRecruitByTime";
    private final String DETAIL_KEY = "op=viewRecruit";

    private static final String ALL_URL = "http://jiuye.uestc.edu.cn/sys/fore.php?op=listRecruit";
    private static final String TODAY_URL = "http://jiuye.uestc.edu.cn/sys/fore.php?op=searchRecruitByTime";
    private static final String DETAIL_URL = "http://jiuye.uestc.edu.cn/sys/fore.php?op=viewRecruit";

    private int index = 0;
    public static boolean todayInfo = false;

    private Site site = Site
            .me()
            .setDomain("uestc.edu.cn")
            .setSleepTime(SLEEP_TIME)
            .setRetryTimes(RETRY_TIME)
            .setUserAgent(AGENT);

    /**
     * 数据解析
     * rec_id : 唯一标示
     * @param page
     */
    @Override
    public void process(Page page){
        String currentUrl = page.getUrl().toString();
        if (currentUrl.contains(ALL_KEY) || currentUrl.contains(TODAY_KEY)) {
            ++index;
            Request request;

            if (todayInfo) {
                request = new Request(TODAY_URL);
            } else {
                request = new Request(ALL_URL);
            }

            Map params = new HashMap<String, Object>(16);
            // 历史数据和今日数据必须参数
            params.put("page", index);
            // 历史数据特有参数
            params.put("rec_type", 2);
            // 今日数据特有参数
            params.put("time_type", 1);

            request.setMethod(HttpConstant.Method.POST);
            request.setRequestBody(HttpRequestBody.form(params, "utf-8"));

            String pageTotal = new JsonPathSelector("$.page").select(page.getRawText());

            List<String> ids = null;
            try {
                ids = new JsonPathSelector("$.data[*].rec_No").selectList(page.getRawText());
            } catch (Exception e) {
                //
            }
            if (CollectionUtils.isNotEmpty(ids)) {
                for (String id : ids) {

                    Request detailRequest = new Request(DETAIL_URL);
                    detailRequest.setMethod(HttpConstant.Method.POST);
                    params = new HashMap<String, Object>(16);
                    params.put("rec_id", id);
                    detailRequest.setRequestBody(HttpRequestBody.form(params, "utf-8"));
                    page.addTargetRequest(detailRequest);
                }
            }
            if (index < Integer.parseInt(pageTotal)) {
                // 继续爬取下一页
                page.addTargetRequest(request);
            }
        } else if(currentUrl.contains(DETAIL_KEY)){
            try {
                // resultContent中的中文字符进行了转义!!需要在pipeline层转义
                String resultContent = page.getRawText();
                page.putField("title", new JsonPathSelector("$.data.rec_title").select(resultContent));
                page.putField("rec_time", new JsonPathSelector("$.data.rec_time").select(resultContent));
                page.putField("rec_way", new JsonPathSelector("$.data.rec_way").select(resultContent));
                page.putField("rec_work_place", new JsonPathSelector("$.data.rec_work_place").select(resultContent));
                page.putField("rec_content", new JsonPathSelector("$.data.rec_content").select(resultContent));
                page.putField("rec_place_name", new JsonPathSelector("$.data.rec_place_name").select(resultContent));

                // rec_basic_info
                page.putField("rec_way", new JsonPathSelector("$.data.rec_basic_info.enter_page").select(resultContent));
                page.putField("enter_kind", new JsonPathSelector("$.data.rec_basic_info.enter_kind").select(resultContent));
                page.putField("enter_address", new JsonPathSelector("$.data.rec_basic_info.enter_address").select(resultContent));
                page.putField("enter_phone", new JsonPathSelector("$.data.rec_basic_info.enter_phone").select(resultContent));
                page.putField("enter_email", new JsonPathSelector("$.data.rec_basic_info.enter_email").select(resultContent));
                page.putField("enter_class", new JsonPathSelector("$.data.rec_basic_info.enter_class").select(resultContent));
                page.putField("enter_size", new JsonPathSelector("$.data.rec_basic_info.enter_size").select(resultContent));
                page.putField("rec_job_nums", new JsonPathSelector("$.data.rec_basic_info.rec_job_nums").select(resultContent));
                page.putField("rec_class", new JsonPathSelector("$.data.rec_basic_info.rec_class").select(resultContent));
                page.putField("enter_intro", new JsonPathSelector("$.data.rec_basic_info.enter_intro").select(resultContent));
            } catch (Exception e) {
                //
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // page rec_type = 2
        String listUrl = "http://jiuye.uestc.edu.cn/sys/fore.php?op=listRecruit";
        // page time_type = 1

        Spider.create(new Uestc()).setDownloader(new HttpClientDownloader()).addUrl(ALL_URL)
                .addPipeline(new ConsolePipeline()).thread(THREAD_COUNT).start();

    }
}
