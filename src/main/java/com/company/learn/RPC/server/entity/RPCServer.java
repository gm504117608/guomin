package com.company.learn.RPC.server.entity;

import com.company.learn.RPC.protocal.Invocation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2016/10/13.
 */
public class RPCServer implements Server {

    private Map<String, Object> serviceEngine = new HashMap<String, Object>(); // 接口服务对象存储
    private int port = 20382;
    private Listener listener;
    private boolean isRuning = true;

    @Override
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setRuning(boolean isRuning) {
        this.isRuning = isRuning;
    }

    @Override
    public void stop() {
        this.setRuning(false);
    }

    @Override
    public boolean isRunning() {
        return isRuning;
    }

    /**
     * 通过反射调用方法
     * @param invo
     */
    @Override
    public void call(Invocation invo) {
        System.out.println(invo.getClass().getName());

        Object obj = serviceEngine.get(invo.getInterfaces().getName());
        if (obj != null) {
            try {
                Method m = obj.getClass().getMethod(invo.getMethod().getMethodName(), invo.getMethod().getParams());
                Object result = m.invoke(obj, invo.getParams());
                invo.setResult(result);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("has no these class");
        }
    }

    /**
     * 注册一个服务到serviceEngine中，使客服端可以调用
     *
     * @param interfaceDefiner
     * @param impl
     */
    @Override
    public void register(Class interfaceDefiner, Class impl) {
        try {
            this.serviceEngine.put(interfaceDefiner.getName(), impl.newInstance());
            System.out.println(serviceEngine);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动线程监听socket的请求
     */
    @Override
    public void start() {
        System.out.println("启动服务器");
        listener = new Listener(this);
        this.isRuning = true;
        listener.start();
    }

}
