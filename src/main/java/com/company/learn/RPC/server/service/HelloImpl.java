package com.company.learn.RPC.server.service;

/**
 * Created by dell on 2016/10/13.
 */
public class HelloImpl implements Hello {
    public String say(String str){
        return "say : " + str;
    }
}
