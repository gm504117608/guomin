package com.company.learn;

import java.util.Date;

/**
 *
 * 1.main线程先执行
 * 2.main线程休眠20秒的时候PeriodicalRunningThread线程开始执行
 * 3.当main线程休眠时间过了就开始执行main线程
 * 4.main线程执行之后再执行PeriodicalRunningThread线程
 *
 */
public class ThreadDemo {

    public static void main(String[] args) {
        PeriodicalRunningThread t = new PeriodicalRunningThread();
        t.start();

        // main线程先于其他线程的执行
        System.out.println("main thread is going to sleep...");
        System.out.println("thread name : " + Thread.currentThread().getName());

        try {
            Thread.currentThread().sleep(20 * 1000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println(new Date() + " now to stop PeriodicalRunningThread");
        t.setRunning(false);

    }

}

class PeriodicalRunningThread extends Thread {

    private volatile boolean running = true;

    @Override
    public void run() {

        while (running) {
            System.out.println(new Date() + " " + Thread.currentThread().getName() + " is running " + new Date());

            try {
                Thread.currentThread().sleep(5 * 1000);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

        System.out.println(new Date() + " " + Thread.currentThread().getName() + " will end");
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}