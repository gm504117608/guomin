package com.company.learn.batchInsert.command.impl;

import com.company.learn.batchInsert.command.Command;

/**
 * Created by dell on 2017/3/6.
 */
public class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println("命令调用者");
        receiver.dealWith();
    }
}
