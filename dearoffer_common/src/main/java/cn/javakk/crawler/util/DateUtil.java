package cn.javakk.crawler.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @Author: javakk
 * @Date: 2019/4/13 14:55
 */
public class DateUtil {

    private final static String YYYYMMDD = "yyyy/MM/dd";
    private final static String YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";

    public final static Long MINUTE = 60L;
    public final static Long HOUR = 3600L;
    public final static Long DAY = 86400L;
    public final static Long MONTH = 2592000L;
    public final static Long YEAR = 31104000L;

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
            passed /= MINUTE;
        } else if (passed >= HOUR && passed < DAY) {
            unit = "小时前";
            passed /= HOUR;
        } else if (passed >= DAY && passed < MONTH) {
            unit = "天前";
            passed /= DAY;
        } else if (passed >= MONTH && passed < YEAR) {
            unit = "个月前";
            passed /= MONTH;
        } else if (passed >= YEAR) {
            synchronized(SIMPLE_FORMAT) {
                return SIMPLE_FORMAT.format(new Date());
            }
        }
        return passed + unit;

    }

    public static void main(String[] args) throws InterruptedException {
        try {
            Date parse = DETAIL_FORMAT.parse("2019/04/14 12:39:29");
            System.out.println(passedString(parse));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
