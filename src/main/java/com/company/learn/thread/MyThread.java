package com.company.learn.thread;

/**
 * 通过继承Thread实现线程的创建
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println(Thread.currentThread().getName() + " === i= " + i);
        }
    }
}
