package com.company.learn.annotation;

import java.lang.annotation.Annotation;

/**
 * Created by dell on 2016/12/22.
 */

public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
        // 这个运行时生成的动态代理对象是可以导出到文件的
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Class<?> clazz = Class.forName("com.company.learn.annotation.PeopleTest");
        Annotation[] an = clazz.getAnnotations();
        for(Annotation a : an){
            if(a instanceof People){
                System.out.println(((People) a).name());
            }
        }
    }
}
