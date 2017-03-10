package com.company.learn.AOP.proxy;

import java.lang.reflect.Proxy;

/**
 * 获取动态代理类并测试执行情况
 * Created by dell on 2017/3/9.
 */
public class Test {

    public static void main(String[] args) {
        // 这个运行时生成的动态代理对象是可以导出到文件的
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(new HelloWorldImpl());
        HelloWorld hw = (HelloWorld) Proxy.newProxyInstance(
                Test.class.getClassLoader(), // 类加载器
                new Class[]{ HelloWorld.class}, // 需要代理的接口
                myInvocationHandler); // 代理类所有方法共同执行的类

        hw.sayHello("妈卖批");

        MyInvocationHandler myInvocationHandler2 = new MyInvocationHandler(new HelloWorldImpl2());
        HelloWorld2 hw2 = (HelloWorld2) Proxy.newProxyInstance(
                Test.class.getClassLoader(), // 类加载器
                new Class[]{HelloWorld2.class}, // 需要代理的接口
                myInvocationHandler2); // 代理类所有方法共同执行的类

        hw2.sayHello2("妈卖批");
    }
}
