package cn.javakk.util;

import java.util.Random;

/**
 * 随机数工具类
 * @Author: javakk
 * @Date: 2019/4/21 13:53
 */
public class RandomUtil {

    private static final Integer VALID_CODE_START = 100000;
    private static final Integer VALID_CODE_END = 900000;


    public static String generateValidCode() {
        int i = new Random().nextInt(VALID_CODE_END) + VALID_CODE_START;
        return String.valueOf(i);
    }

}
