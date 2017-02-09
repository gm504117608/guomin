package com.company.learn.excel.excelimport.util;

public class StringUtil {
    private StringUtil() {
    }

    /**
     * 判断字符串是否为空
     * @param str 需要处理的字符串
     * @return 为空返回 true 否则 false
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() <= 0 || str.trim() == null || str.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     * @param str 需要处理的字符串
     * @return 为空返回 false 否则 true
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}