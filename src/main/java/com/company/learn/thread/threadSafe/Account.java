package com.company.learn.thread.threadSafe;

/**
 * 账户信息实体
 * 模拟银行账户信息
 * 存取款业务
 *
 * 注意：
 * 1.wait()方法执行后，当前线程立即进入到等待阻塞状态，其后面的代码不会执行；
 *
 * 2.notify()/notifyAll()方法执行后，将唤醒此同步锁对象上的（任意一个-notify()/所有-notifyAll()）线程对象，
 * 但是，此时还并没有释放同步锁对象，也就是说，如果notify()/notifyAll()后面还有代码，
 * 还会继续进行，知道当前线程执行完毕才会释放同步锁对象；
 *
 * 3.notify()/notifyAll()执行后，如果右面有sleep()方法，则会使当前线程进入到阻塞状态，但是同步锁对象没有释放，
 * 依然自己保留，那么一定时候后还是会继续执行此线程，接下来同2；
 *
 * 4.wait()/notify()/nitifyAll()完成线程间的通信或协作都是基于相同的同步锁对象，因此，如果是不同的同步锁对象将失去意义，
 * 同时，同步锁对象最好是与共享资源对象保持一一对应关系；
 *
 * 5.当wait线程唤醒后并执行时，是接着上次执行到的wait()方法代码后面继续往下执行的。

 */
public class Account {

    private String accountId; // 账户id
    private double balance; // 剩余钱数
    // 标识账户中是否已有存款
    private boolean flag = false;

    public Account() {
    }

    public Account(String accountId, Double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 存钱
     * @param depositAmount
     */
    public synchronized void deposit(double depositAmount, int i) {
        if (flag) {
            // 账户中已有人存钱进去，此时当前线程需要等待阻塞
            try {
                System.out.println(Thread.currentThread().getName() + " 开始要执行wait操作-- i=" + i);
                wait();
                // 1
                System.out.println(Thread.currentThread().getName() + " 执行了wait操作-- i=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // 开始存钱
            System.out.println(Thread.currentThread().getName() + " 存款:" + depositAmount + " -- i=" + i);
            setBalance(balance + depositAmount);
            flag = true;
            // 唤醒其他线程
            notifyAll();
            // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -- 存钱 -- 执行完毕-- i=" + i
                    + " 剩余钱数：" + getBalance());
        }
    }

    /**
     * 取钱
     * @param drawAmount
     */
    public synchronized void draw(double drawAmount, int i) {
        if (!flag) {
            // 账户中还没人存钱进去，此时当前线程需要等待阻塞
            try {
                System.out.println(Thread.currentThread().getName() + " 开始要执行wait操作 执行了wait操作" + " -- i=" + i);
                wait();
                System.out.println(Thread.currentThread().getName() + " 执行了wait操作--i=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // 开始取钱
            System.out.println(Thread.currentThread().getName() + " 取钱：" + drawAmount + " -- i=" + i);
            setBalance(getBalance() - drawAmount);
            flag = false;
            // 唤醒其他线程
            notifyAll();
            System.out.println(Thread.currentThread().getName() + " -- 取钱 -- 执行完毕-- i=" + i
                    + " 剩余钱数：" + getBalance()); // 3
        }
    }

}

