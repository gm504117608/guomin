package com.company.learn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dell on 2016/10/9.
 */
public class ReentrantLockDemo {

    /**
     * 通过使用synchronized、ReentrantLock来控制线程安全性，同时比较性能好坏
     */
    public static void demoLock(){

        final int loopCount = 10000;
        int threadCount = 10;
        final SafeSeqWithLock seq = new SafeSeqWithLock();
        final CountDownLatch l = new CountDownLatch(threadCount);

        long start = System.currentTimeMillis();

        for(int i=0; i<threadCount; i++){
            final int index = i;
            new Thread(new Runnable(){
                public void run(){
                    for(int j=0; j<loopCount; j++){
                        seq.inc();
                    }
                    System.out.println("finished : " + index);
                    l.countDown();
                }
            }).start();

        }
        try{
            l.await();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("both have finished....");

        System.out.println("SafeSeqWithLock:" + seq.get());

        long end = System.currentTimeMillis();
        System.out.println("execute time : " + (end-start));
    }

    /**
     * 通过使用AtomicInteger来控制线程安全性
     */
    public static void demoLock2(){
        final int loopCount = 10000;
        int threadCount = 10;
        final CountDownLatch l = new CountDownLatch(threadCount);
        AtomicInteger ai = new AtomicInteger();
        long start = System.currentTimeMillis();

        for(int i=0; i<threadCount; i++){
            final int index = i;
            new Thread(new Runnable(){
                public void run(){
                    for(int j=0; j<loopCount; j++){
                        ai.incrementAndGet();
                    }
                    System.out.println("finished : " + index);
                    l.countDown();
                }
            }).start();

        }
        try{
            l.await();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("both have finished....");

        System.out.println("SafeSeqWithLock:" + ai.get());

        long end = System.currentTimeMillis();
        System.out.println("execute time : " + (end-start));

    }
}

class SafeSeqWithLock{

    private long count = 0;
    // 使用ReentrantLock来保证线程安全，他比使用 synchronized 要高效很多
    private ReentrantLock lock = new ReentrantLock();
    public void inc(){
        lock.lock();
        try{
           count ++;
        }finally{
            lock.unlock();
        }
    }

    // 速度比使用lock慢很多，性能不行
//    public synchronized void inc(){
//        count ++;
//    }

    public long get(){
        return count;
    }


}













