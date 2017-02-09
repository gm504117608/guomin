package com.company.learn.RPC.server;


import com.company.learn.RPC.server.entity.RPCServer;
import com.company.learn.RPC.server.entity.Server;
import com.company.learn.RPC.server.service.Echo;
import com.company.learn.RPC.server.service.EchoImpl;
import com.company.learn.RPC.server.service.Hello;
import com.company.learn.RPC.server.service.HelloImpl;

/**
 * Created by dell on 2016/10/13.
 */
public class MainServer {
    public static void main(String[] args) {
        // 创建一个Server
        Server server = new RPCServer();
        // 注册服务提供客服端调用
        server.register(Echo.class, EchoImpl.class);
        server.register(Hello.class, HelloImpl.class);
        // 启动线程来监听socket请求
        server.start();
    }
}
