package com.company.learn.IOC;

/**
 * Created by lenovo on 2016/8/13.
 */
public class Hello implements IHello {

    public void sayHello(String name) {
        System.out.println("Hello " + name + "!");
    }
}
