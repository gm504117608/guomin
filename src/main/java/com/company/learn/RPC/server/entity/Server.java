package com.company.learn.RPC.server.entity;

import com.company.learn.RPC.protocal.Invocation;

/**
 * Created by dell on 2016/10/13.
 */
public interface Server {

    void stop();
    void start();
    void register(Class interfaceDefiner, Class impl);
    void call(Invocation invo);
    boolean isRunning();
    int getPort();

}
