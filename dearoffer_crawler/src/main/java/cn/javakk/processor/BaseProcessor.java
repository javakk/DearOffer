package cn.javakk.processor;

import java.util.Date;
import java.util.Random;

/**
 * @Author: JavaKK
 * @Date: 2019/4/8 9:49
 */
public interface BaseProcessor {
    /**
     * 爬虫线程数
     */
    Integer THREAD_COUNT = 5;
    /**
     * 失败重试次数
     */
    Integer RETRY_TIME = 3;
    /**
     * 休眠时间
     */
    Integer SLEEP_TIME = 3000;

    /**
     * 客户端
     */
    String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36";

    /**
     * 是否全量采集
     */
    Boolean fullCollect = true;

    /**
     * 上次采集时间
     */
    Date lastCollectTime = new Date();
}
