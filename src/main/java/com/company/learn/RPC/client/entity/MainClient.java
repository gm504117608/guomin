package com.company.learn.RPC.client.entity;

import com.company.learn.RPC.server.service.Hello;

/**
    服务器端有 Interface 和 Implement 类（实现类）,
    客户端只有 Interface .
    原理：
    服务器启动了一个线程监听 Socket 端口,
    有Socket访问了, 反序列化解析出
    调用哪个Service 哪个 方法, 以及传入的 参数,
    再用Socket 写回去.

    客户端 利用 Jdk 的Proxy 生成了一个代理类,
    在创建 Proxy 时建立与服务器的Socket连接.
    调用 Proxy 的方法时, 向服务器发送数据, 等待结果返回.
 */
public class MainClient {
    public static void main(String[] args) {
//        Echo echo = RPC.getProxy(Echo.class, "127.0.0.1", 20382);
//
//        System.out.println(echo.print("hello,hello"));
//        System.out.println(echo.print("hellow,rod"));
//        System.out.println(echo.print("hellow,rod"));
//        System.out.println(echo.print("hellow,rod"));
//        System.out.println(echo.print("hellow,rod"));
//        System.out.println(echo.print("hellow,rod"));
//        System.out.println(echo.print("hellow,rod"));

        Hello hello = RPC.getProxy(Hello.class, "127.0.0.1", 20382);
        System.out.println(hello.say("how are you!"));

    }

}
