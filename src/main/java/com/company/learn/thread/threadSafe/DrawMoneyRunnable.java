package com.company.learn.thread.threadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 存取钱的操作
 */
public class DrawMoneyRunnable implements Runnable {

    private static Account account; // 账户信息
    private static double money; // 取出的钱数
    // 显示定义Lock同步锁对象，此对象与共享资源具有一对一关系
    private static Lock lock = new ReentrantLock();

    public DrawMoneyRunnable(Account account, double money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        getMoney();
    }

    public void getMoney() {
        lock.lock();
        System.out.println("进入的人：" + Thread.currentThread().getName());
        try {
            if (account.getBalance() > money) {
                System.out.println("取钱的人：" + Thread.currentThread().getName());
                System.out.println("取出钱数：" + Thread.currentThread().getName() + "==" + money);
                account.setBalance(account.getBalance() - money);
                System.out.println("剩余钱数：" + Thread.currentThread().getName() + "==" + account.getBalance());
            }
        } finally {
            lock.unlock();
        }
    }

//    public void getMoney() {
//        synchronized (account) {
//            System.out.println("进入的人：" + Thread.currentThread().getName());
//            if (account.getBalance() > money) {
//                System.out.println("取钱的人：" + Thread.currentThread().getName());
//                System.out.println("取出钱数：" + Thread.currentThread().getName() + "==" + money);
//                account.setBalance(account.getBalance() - money);
//                System.out.println("剩余钱数：" + Thread.currentThread().getName() + "==" + account.getBalance());
//            }
//        }
//    }

//    public synchronized static void getMoney() {
//        System.out.println("进入的人：" + Thread.currentThread().getName());
//        if (account.getBalance() > money) {
//            System.out.println("取钱的人：" + Thread.currentThread().getName());
//            System.out.println("取出钱数：" + Thread.currentThread().getName() + "==" + money);
//            account.setBalance(account.getBalance() - money);
//            System.out.println("剩余钱数：" + Thread.currentThread().getName() + "==" + account.getBalance());
//        }
//    }
//

}
