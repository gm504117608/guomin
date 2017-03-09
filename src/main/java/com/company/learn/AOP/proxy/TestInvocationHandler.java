package com.company.learn.AOP.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 * Created by dell on 2017/3/9.
 */
public class TestInvocationHandler implements InvocationHandler {


    private Object target;

    public TestInvocationHandler(Object target){
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("Before invocation");

        Object retVal = method.invoke(target, args);

        System.out.println("After invocation");

        return retVal;
    }
}
