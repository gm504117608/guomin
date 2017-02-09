package com.company.learn.thread.threadSafe;

/**
 * 模拟父亲、母亲去同一个账户里面取钱的操作，
 * 线程不安全的情况下面会出现钱数不正确
 */
public class Test {

    public static void main(String[] args) {
        Account account = new Account("123", 1000.00);
        DrawMoneyRunnable Father = new DrawMoneyRunnable(account, 700.00);
        DrawMoneyRunnable mother = new DrawMoneyRunnable(account, 600.00);

        Thread t1 = new Thread(Father);
        t1.setName("father");
        Thread t2 = new Thread(mother);
        t2.setName("mother");

        t1.start();
        t2.start();
    }
}
