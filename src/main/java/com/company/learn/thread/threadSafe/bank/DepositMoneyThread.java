package com.company.learn.thread.threadSafe.bank;

import com.company.learn.thread.threadSafe.Account;

/**
 * 存款业务线程
 */
public class DepositMoneyThread extends Thread {

    private Account account;
    private double amount;

    public DepositMoneyThread(String threadName, Account account, double amount) {
        super(threadName);
        this.account = account;
        this.amount = amount;
    }

    /**
     * 存钱
     */
    public void run() {
        for (int i = 0; i < 5; i++) {
            account.deposit(amount, i);
        }
    }
}

