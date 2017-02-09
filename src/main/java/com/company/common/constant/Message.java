package com.company.common.constant;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private static String DATA = "data";
    private static String STATUS = "status";

    public static Object ok(Object obj) {
        Object result = dealWithMessage(obj, 200);
        return result;
    }

    public static Object error(Object obj) {
        Object result = dealWithMessage(obj, 500);
        return result;
    }

    public static Object noContent(Object obj) {
        Object result = dealWithMessage(obj, 204);
        return result;
    }

    private static Object dealWithMessage(Object obj, int status) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(DATA, obj);
        result.put(STATUS, status);
        return result;
    }
}