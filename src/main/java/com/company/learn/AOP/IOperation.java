package com.company.learn.AOP;

import java.lang.reflect.Method;

/**
 * Created by lenovo on 2016/8/13.
 */
public interface IOperation {
    /**
     * 方法执行之前的操作
     *
     * @param method
     */
    void start(Method method);

    /**
     * 方法执行之后的操作
     *
     * @param method
     */
    void end(Method method);
}
