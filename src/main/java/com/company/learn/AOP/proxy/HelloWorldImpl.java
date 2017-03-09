package com.company.learn.AOP.proxy;

/**
 * Created by dell on 2017/3/9.
 */
public class HelloWorldImpl implements HelloWorld {
    @Override
    public void sayHello(String name){
        System.out.println(name);
    }
}
