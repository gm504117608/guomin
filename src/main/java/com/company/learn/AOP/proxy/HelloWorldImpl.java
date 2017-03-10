package com.company.learn.AOP.proxy;

/**
 * 目标接口实现类
 */
public class HelloWorldImpl implements HelloWorld {
    @Override
    public void sayHello(String name){
        System.out.println(name);
    }
}
