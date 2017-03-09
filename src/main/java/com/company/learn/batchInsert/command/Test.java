package com.company.learn.batchInsert.command;

import com.company.learn.batchInsert.command.impl.Invoker;
import com.company.learn.batchInsert.command.impl.MyCommand;
import com.company.learn.batchInsert.command.impl.Receiver;

/**
 * Command 是命令调用的接口；
 * MyCommand 是命令调用的具体实现类；
 * Invoker是命令的发出者；
 * Receiver是命令的执行者；
 * 命令模式就是通过命令调用者将命令的发出和执行的过程进行解耦。
 */
public class Test {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new MyCommand(receiver);
        Invoker invoker = new Invoker(command);

        invoker.sendOut();
    }

}
