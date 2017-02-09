package com.company.learn.RPC.protocal;

import java.io.Serializable;

/**
 * 传输参数的封装类
 */
public class Method implements Serializable{

    private static final long serialVersionUID = 1L;

    private String methodName; // 方法名称
    private Class[] params; // 方法类型

    public Method(String name, Class<?>[] parameterTypes) {
        this.methodName = name;
        this.params = parameterTypes;
    }

    public Class[] getParams() {
        return params;
    }

    public void setParams(Class[] params) {
        this.params = params;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
