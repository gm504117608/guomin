package com.company.learn.RPC.protocal;

import java.io.Serializable;
import java.util.Arrays;


/**
 * 传输参数的封装类
 */
public class Invocation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Class interfaces; // 接口类
    private Method method; // 方法信息对象
    private Object[] params; // 参数值
    private Object result; // 结果值

    public Class getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class interfaces) {
        this.interfaces = interfaces;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "interfaces=" + interfaces +
                ", method=" + method +
                ", params=" + Arrays.toString(params) +
                ", result=" + result +
                '}';
    }
}
