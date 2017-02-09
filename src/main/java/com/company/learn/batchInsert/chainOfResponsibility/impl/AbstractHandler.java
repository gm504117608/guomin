package com.company.learn.batchInsert.chainOfResponsibility.impl;

import com.company.learn.batchInsert.chainOfResponsibility.Handler;

/**
 * Created by dell on 2016/12/13.
 */
public class AbstractHandler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
