package com.company.learn.thread;

/**
 * 实现Runnable接口 通过一个开关控制线程停止
 */
public class MyRunnable2 implements Runnable {

    private boolean running = true;

    @Override
    public void run() {
        int i = 0;
        while(running){
            System.out.println(Thread.currentThread().getName() + " === i= " + i);
            i++;
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
