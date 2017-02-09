package com.company.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 后端返回前端数据封装
 */
public class ResultData {

    private String code; // 返回状态代码
    private String message; // 返回错误信息
    private Object data; // 返回数据

    public Object getData() {
        return data;
    }
    /**
     * 将所有的返回数据转换为json
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
