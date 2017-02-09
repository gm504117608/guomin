package com.company.learn.thread.threadSafe.bank;

import com.company.learn.thread.threadSafe.Account;

/**
 * Created by dell on 2016/12/30.
 */
public class Test {

    public static void main(String[] args) {
        Account account = new Account("123456", 0.00);
        Thread drawMoneyThread = new DrawMoneyThread("取钱线程", account, 700);
        Thread depositMoneyThread = new DepositMoneyThread("存钱线程", account, 700);
        drawMoneyThread.start();
        depositMoneyThread.start();
    }
}
