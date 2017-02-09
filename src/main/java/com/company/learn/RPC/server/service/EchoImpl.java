package com.company.learn.RPC.server.service;

/**
 * 服务端提供的服务实现类
 */
public class EchoImpl implements Echo {
    public String print(String str){
        return "from remote:" + str;
    }
}
