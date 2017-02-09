package com.company.learn.thread.threadSafe.bank;

import com.company.learn.thread.threadSafe.Account;

/**
 * 取款业务线程
 */
public class DrawMoneyThread extends Thread {

    private Account account;
    private double amount;

    public DrawMoneyThread(String threadName, Account account, double amount) {
        super(threadName);
        this.account = account;
        this.amount = amount;
    }

    /**
     * 取钱
     */
    public void run() {
        for (int i = 0; i < 5; i++) {
            account.draw(amount, i);
        }
    }

}
