package com.company.learn.excel.excelExport2.util;

import java.util.Date;

/**
 * 日期格式处理类
 */
public class DateUtil {
    private DateUtil() {
    }

    /**
     * 返回:20100910210637578
     * @param date 日期
     * @return
     */
    public static String format(Date date) {
        if (date == null) {
            return "";
        }
        return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", date);
    }

    /**
     * 返回:2010-09-10 21:08:17
     * @param date 日期
     */
    public static String formatYMDHMS(Date date) {
        if (date == null) {
            return "";
        }
        return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", date);
    }

    /**
     * 返回:2010-09-10
     * @param date 日期
     */
    public static String formatYMD(Date date) {
        if (date == null) {
            return "";
        }
        return String.format("%1$tY-%1$tm-%1$td", date);
    }

    /**
     * 返回:2010年09月10日
     * @param date 日期
     */
    public static String formatYMD_CN(Date date) {
        if (date == null) {
            return "";
        }
        return String.format("%1$tY年%1$tm月%1$td日", date);
    }

    /**
     * 返回:09-10
     * @param date 日期
     */
    public static String formatMD(Date date) {
        if (date == null) {
            return "";
        }
        return String.format("%1$tm-%1$td", date);
    }

    /**
     * 返回:09月10日
     * @param date 日期
     */
    public static String formatMD_CN(Date date) {
        if (date == null) {
            return "";
        }
        return String.format("%1$tm月%1$td日", date);
    }

    public static void main(String[] args) {
        System.out.println(format(new Date()));
        System.out.println(formatYMDHMS(new Date()));
        System.out.println(formatYMD(new Date()));
        System.out.println(formatYMD_CN(new Date()));
        System.out.println(formatMD(new Date()));
        System.out.println(formatMD_CN(new Date()));
    }
}