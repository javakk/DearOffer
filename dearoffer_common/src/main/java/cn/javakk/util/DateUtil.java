package cn.javakk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @Author: javakk
 * @Date: 2019/4/13 14:55
 */
public class DateUtil {

    private final static String YYYYMMDD = "YYYY/MM/DD";
    private final static String YYYYMMDDHHMMSS = "YYYY/MM/DD HH:MM:SS";

    private final static Long MINUTE = 60L;
    private final static Long HOUR = 3600L;
    private final static Long DAY = 86400L;
    private final static Long MONTH = 259200L;
    private final static Long YEAR = 31536000L;

    /**
     *  SimpleDateFormat是线程不安全的，一般不定义为static变量，但可以加锁保证安全性
     */
    private final static SimpleDateFormat SIMPLE_FORMAT = new SimpleDateFormat(YYYYMMDD);
    private final static SimpleDateFormat DETAIL_FORMAT = new SimpleDateFormat(YYYYMMDDHHMMSS);

    /**
     * 获取当前时刻
     * @return
     */
    public static Date getNow(){
        return new Date();
    }

    /**
     * 过去了多少秒
     * @param date
     * @return
     */
    public static Long howLongPassed(Date date) {
        return (System.currentTimeMillis() - date.getTime()) / 1000;
    }

    /**
     * 格式化的过去时间串
     * @param date
     * @return
     */
    public static String passedString(Date date) {
        Long passed = howLongPassed(date);
        String unit = "秒前";
        if (passed >= MINUTE && passed < HOUR) {
            unit = "分钟前";
        } else if (passed >= HOUR && passed < DAY) {
            unit = "小时前";
        } else if (passed >= DAY && passed < MONTH) {
            unit = "天前";
        } else if (passed >= MONTH && passed < YEAR) {
            unit = "个月前";
        } else if (passed >= YEAR) {
            synchronized(SIMPLE_FORMAT) {
                return SIMPLE_FORMAT.format(new Date());
            }
        }
        return passed + unit;

    }

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        Thread.sleep(3000);
        System.out.println(passedString(date));

    }
}
