package com.company.learn.IOC;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;

/**
 * Created by lenovo on 2016/8/16.
 */
public class Test {

    public void ApplicationContextTest1(){
        // 从配置xml文件中实例化一个ioc容器
        ApplicationContext act = new ClassPathXmlApplicationContext("learn/ApplicationContextLearn.xml");
        // 从ioc容器中获取hello的实例对象
        // 从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
        IHello h = act.getBean("iHello", IHello.class);
        // 需要进行强制转换
        IHello h2 = (Hello) act.getBean("iHello");

        // 调用获取对象的方法
        h.sayHello("World！SB ClassPathXmlApplicationContext");
        h2.sayHello("World！SB2 ClassPathXmlApplicationContext");
    }

    public void ApplicationContextTest2(){
        // 从配置xml文件中实例化一个ioc容器
        ApplicationContext act2 = new FileSystemXmlApplicationContext("D:\\Workspaces-idea\\SpringMVC\\SpringMVCModule\\src\\main\\resources\\learn\\ApplicationContextLearn.xml");
        // 从ioc容器中获取hello的实例对象
        // 从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
        // 需要进行强制转换
        IHello h = (Hello) act2.getBean("iHello");

        // 调用获取对象的方法
        h.sayHello("SB, it is you! FileSystemXmlApplicationContext");

    }

    public void ApplicationContextTest3(){
        // 从配置xml文件中实例化一个ioc容器
        File file = new File("D:\\Workspaces-idea\\SpringMVC\\SpringMVCModule\\src\\main\\resources\\learn\\ApplicationContextLearn.xml");
        Resource resource = new FileSystemResource(file);
        BeanFactory bf = new XmlBeanFactory(resource);

        // 从ioc容器中获取hello的实例对象
        // 从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
        // 需要进行强制转换
        IHello h = (Hello) bf.getBean("iHello");

        // 调用获取对象的方法
        h.sayHello("SB, it is you! XmlBeanFactory");
    }

    public void ApplicationContextTest4(){
        // 从配置xml文件中实例化一个ioc容器
        Resource resource = new ClassPathResource("learn/ApplicationContextLearn.xml");
        BeanFactory bf = new XmlBeanFactory(resource);

        // 从ioc容器中获取hello的实例对象
        // 从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
        // 需要进行强制转换
        IHello h = (Hello) bf.getBean("iHello");

        // 调用获取对象的方法
        h.sayHello("SB, it is you! ClassPathResource");

    }

    public static void main(String[] args){
        Test t = new Test();
        t.ApplicationContextTest1();
        t.ApplicationContextTest2();
        t.ApplicationContextTest3();
        t.ApplicationContextTest4();

    }
}
