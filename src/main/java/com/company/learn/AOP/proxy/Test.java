package com.company.learn.AOP.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by dell on 2017/3/9.
 */
public class Test {

    public static void main(String[] args) {
        // 这个运行时生成的动态代理对象是可以导出到文件的
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        TestInvocationHandler test = new TestInvocationHandler(new HelloWorldImpl());
        HelloWorld hw = (HelloWorld) Proxy.newProxyInstance(
                HelloWorld.class.getClassLoader(),
                new Class[]{HelloWorld.class},
                test);
        hw.sayHello("妈卖批");
    }
}
