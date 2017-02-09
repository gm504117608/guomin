package com.company.learn.AOP;

/**
 * Created by lenovo on 2016/8/13.
 */
public class Test {
    public static void main(String[] args){
        // 这个运行时生成的动态代理对象是可以导出到文件的
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IHello hello = (IHello) new DynaProxyHello().bind(new Hello(), new Operation());
        hello.sayGoodBye("Dead");
        hello.sayHello("Dead");

        System.out.println(hello.getClass().getName());

//        System.out.println("==================================");
//        System.out.println(ClassLoader.getSystemClassLoader());

//          ApplicationContext ctx= new ClassPathXmlApplicationContext(
//                "spring/ws-client.xml");
//
//          Hello cache = (Hello) ctx.getBean("cache");
    }
}
