package com.company.learn.pay.wxpay.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * nonce_str随即字符串
 *
 */
public class RandCharsUtils {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 字符串数组
     */
    private final static String[] array = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 字符串数组长度
     */
    private static final int len = 62;

    /**
     * 字符串
     */
    private final static String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 随机获取指定长度的字符串
     * @param length 表示生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int number;
        int len = str.length();
        for (int i = 0; i < length; i++) {
            number = random.nextInt(len);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 随机获取指定长度的字符串
     * @param length 表示生成字符串的长度
     * @return
     */
    public static String getRandomStringByArray(int length) {
        Random r = new Random();
        StringBuilder str = new StringBuilder();
        int result;
        for (int i = 0; i < length; i++) {
            result = r.nextInt(len);
            str.append(array[result]);
        }
        return str.toString();
    }

    /**
     * 订单开始交易时间
     */
    public static String timeStart() {
        return df.format(new Date());
    }

    /**
     * 订单失效时间
     */
    public static String timeExpire() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 30);
        return df.format(now.getTimeInMillis());
    }


    public static void main(String[] args) {
        /*for (int i = 0; i < 10; i++) {
            System.out.println("第"+i+"次是："+getRandomString(32));
		}*/


        System.out.println("开始时间是：" + timeStart());
        System.out.println("开始时间是：" + timeExpire());
    }

}
