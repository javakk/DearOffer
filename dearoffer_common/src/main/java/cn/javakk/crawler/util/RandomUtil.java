package cn.javakk.crawler.util;

import java.util.Random;

/**
 * 随机数工具类
 * @Author: javakk
 * @Date: 2019/4/21 13:53
 */
public class RandomUtil {

    private static final Integer VALID_CODE_START = 100000;
    private static final Integer VALID_CODE_END = 900000;

    private static final char[] SALT_ALPHABET = "!@#$%^&*()_+QWERTYUIOP{}ASDFGHJKL:ZXCVBNM<>?1234567890".toCharArray();
    private static final Integer SALT_ALPHABET_LENGTH = SALT_ALPHABET.length;
    private static final Integer SALT_LENGTH = 10;


    public static String generateValidCode() {
        int i = new Random().nextInt(VALID_CODE_END) + VALID_CODE_START;
        return String.valueOf(i);
    }

    public static String generateSalt() {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < SALT_LENGTH; i++) {
            buffer.append(SALT_ALPHABET[random.nextInt(SALT_ALPHABET_LENGTH)]);
        }
        return new String(buffer);
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(generateSalt());
//        }
//    }

}
