package com.company.learn.thread;

/**
 * 实现Runnable接口
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName() + " === i= " + i);
        }
    }
}
