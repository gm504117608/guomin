package com.company.learn.RPC.client.entity;

import com.company.learn.RPC.protocal.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dell on 2016/10/13.
 */
public class RPC {

    /**
     * 通过动态代理调用远程的server服务
     * @param clazz
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    public static <T> T getProxy(final Class<T> clazz,String host,int port) {
        // 初始化Client类
        final Client client = new Client(host,port);
        InvocationHandler handler = new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 初始化需要传输的参数
                Invocation invo = new Invocation();
                // 接口名称
                invo.setInterfaces(clazz);
                // 方法名称、参数类型
                invo.setMethod(new com.company.learn.RPC.protocal.Method(method.getName(), method.getParameterTypes()));
                // 设置参数值
                invo.setParams(args);
                // 通过client使用socket调用远程的服务
                client.call(invo);
                return invo.getResult();
            }
        };
        T t = (T) Proxy.newProxyInstance(RPC.class.getClassLoader(), new Class[] {clazz}, handler);
        return t;
    }
}
