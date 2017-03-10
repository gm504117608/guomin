package com.company.learn.AOP.proxy;

/**
 * Created by dell on 2017/3/10.
 */
public class HelloWorldImpl2 implements HelloWorld2 {
    @Override
    public void sayHello2(String name){
        System.out.println(name + "HelloWorldImpl2");
    }
}
