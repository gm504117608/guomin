package com.company.learn.batchInsert.command.impl;

import com.company.learn.batchInsert.command.Command;

/**
 * Created by dell on 2017/3/6.
 */
public class Invoker {
    private Command command;
    public Invoker(Command command){
        this.command = command;
    }

    public void sendOut(){
        System.out.println("命令发出者");
        command.execute();
    }
}
