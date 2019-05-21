package cn.javakk.crawler.processor.position;

import cn.javakk.crawler.processor.BaseProcessor;
import cn.javakk.util.DateUtil;
import cn.javakk.entity.Position;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.utils.HttpConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 成电职位信息爬虫
 *
 * @Author: javakk
 * @Date: 2019/5/21 13:20
 */
@Component
public class Uestc implements PageProcessor, BaseProcessor {

    private final static String LIST_LINK = "http://jiuye.uestc.edu.cn/sys/fore.php?op=listRecruit";

    private final String ALL_WORD = "listRecruit";
    private static final String DETAIL_URL = "http://jiuye.uestc.edu.cn/sys/fore.php?op=viewRecruit";

    private static int index = 0;


    @Autowired
    private RedisTemplate redisTemplate;

    private Site site = Site
            .me()
            .setDomain("uestc.edu.cn")
            .setSleepTime(SLEEP_TIME)
            .setRetryTimes(RETRY_TIME)
            .setCharset("UTF-8")
            .setUserAgent(AGENT);

    @Override
    public void process(Page page) {
        SetOperations opsForSet = redisTemplate.opsForSet();
        String resultContent = page.getRawText();

        if (page.getUrl().toString().contains(ALL_WORD)) {
            // 列表页
            String pageStr = new JsonPathSelector("$.page").select(resultContent);
            // id信息采集
            List<String> nodes = new JsonPathSelector("$.data").selectList(resultContent);

            Map params = new HashMap<String, Object>(16);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
            for (String node : nodes) {
                String id = new JsonPathSelector("$.rec_No").select(node);
                String rec_publish_time = new JsonPathSelector("$.rec_publish_time").select(node);

                // 增量采集
                if (StringUtils.isNotBlank(rec_publish_time)) {
                    try {
                        Date publishedDate = simpleDateFormat.parse(rec_publish_time);
                        DateUtil.howLongPassed(publishedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


                // 继续抓取
                Request detailRequest = new Request(DETAIL_URL);
                detailRequest.setMethod(HttpConstant.Method.POST);
                params = new HashMap<String, Object>(16);
                params.put("rec_id", id);
                detailRequest.setRequestBody(HttpRequestBody.form(params, "utf-8"));
                page.addTargetRequest(detailRequest);
            }

            int total = Integer.parseInt(pageStr);


            index += 1;
            if (index > total) {
                page.setSkip(true);
                return;
            } else {
                Request request = new Request(LIST_LINK);
                request.setMethod("post");
                Map map = new HashMap<>(16);
                map.put("rec_way", 2);
                map.put("page", index);
                request.setRequestBody(HttpRequestBody.form(map, "utf-8"));
                page.addTargetRequest(request);
            }
        } else {
            try {
                String id = new JsonPathSelector("$.data.rec_No").select(resultContent);
                Boolean member = opsForSet.isMember("crawler:position",  id);
                if (!member) {
                    Position position = new Position();
                    position.setId(UUID.randomUUID().toString());
                    position.setCreateTime(DateUtil.getNow());
                    position.setStatus(1);
                    position.setSource(page.getUrl().toString());
                    position.setAddress(new JsonPathSelector("$.data.rec_basic_info.enter_address").select(resultContent));
                    position.setIndustry(new JsonPathSelector("$.data.rec_basic_info.enter_class").select(resultContent));
                    position.setEmail(new JsonPathSelector("$.data.rec_basic_info.enter_email").select(resultContent));
                    position.setIntroduction(new JsonPathSelector("$.data.rec_basic_info.enter_intro").select(resultContent));
                    position.setKind(new JsonPathSelector("$.data.rec_basic_info.enter_kind").select(resultContent));
                    position.setPhone(new JsonPathSelector("$.data.rec_basic_info.enter_phone").select(resultContent));
                    position.setScale(new JsonPathSelector("$.data.rec_basic_info.enter_size").select(resultContent));
                    position.setDescription(new JsonPathSelector("$.data.rec_content").select(resultContent));
                    position.setDegreeTag(new JsonPathSelector("$.data.rec_degree").select(resultContent));
                    position.setCompanyName(new JsonPathSelector("$.data.rec_enter_name").select(resultContent));
                    position.setTitle(new JsonPathSelector("$.data.rec_title").select(resultContent));
                    position.setWay(new JsonPathSelector("$.data.rec_way").select(resultContent));
                    position.setCityTag(new JsonPathSelector("$.data.rec_work_place").select(resultContent));
                    position.setPageView(Long.valueOf(new JsonPathSelector("$.data.rec_nums").select(resultContent)));

                    page.putField("position", position);
                    opsForSet.add("crawler:position", id);
                } else {
                    page.setSkip(true);
                }

            } catch (Exception e) {
                System.out.println("====UESTC职位信息爬虫，发生异常====" + e);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

//    public static void main(String[] args) {
//        String link = "http://jiuye.uestc.edu.cn/sys/fore.php?op=listRecruit";
//
//        Request request = new Request(link);
//        request.setMethod("post");
//        Map map = new HashMap<>(16);
//        map.put("rec_way", 2);
//        map.put("page", 1);
//        request.setRequestBody(HttpRequestBody.form(map, "utf-8"));
//        Spider.create(new Uestc()).setDownloader(new HttpClientDownloader()).addRequest(request)
//                .addPipeline(new MysqlPipeline()).thread(THREAD_COUNT).start();
//    }
}
