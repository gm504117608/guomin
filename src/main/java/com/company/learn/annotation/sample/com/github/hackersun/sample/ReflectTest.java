package com.company.learn.annotation.sample.com.github.hackersun.sample;


import com.company.learn.annotation.annotation.com.github.hackersun.annotation.Reflect;
import com.company.learn.annotation.processor.com.github.hackersun.processor.reflect.ReflectProcessor;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/20
 */
public class ReflectTest {

    @Reflect
    public static void sayHello(final String name) {
        System.out.println("==>> Hi, " + name + " [sayHello]");
    }

    @Reflect(name = "AngelaBaby")
    public static void sayHelloToSomeone(final String name) {
        System.out.println("==>> Hi, " + name + " [sayHelloToSomeone]");
    }

    public static void main(final String[] args) throws Exception {
        final ReflectProcessor pm = new ReflectProcessor();
        pm.parseMethod(ReflectTest.class);
    }
}
