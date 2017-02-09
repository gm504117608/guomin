package com.company.learn.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dell on 2016/10/11.
 */
public class AtomicTest {

    Lock lock = new ReentrantLock();
    int a = 0;

    AtomicInteger b = new AtomicInteger(0);

    public void add(){
        System.out.println(Thread.currentThread().getName());
        lock.lock();
        try{
            a++;
        }finally{
            lock.unlock();
        }
    }

    // 由于a是线程不安全的 导致计算出来的结果不正确
    public void test() throws InterruptedException {
        final int threadSize = 10;
        Thread[] td = new Thread[threadSize];
        for(int i=0; i<threadSize; i++){
            td[i] = new Thread(){
                public void run(){
//                    add();
                    b.incrementAndGet();
                    System.out.println(Thread.currentThread().getName());
                }
            };
        }

        for(Thread t : td){
            t.start();
//            t.join(); // 保证线程按顺序执行
        }

        for(Thread t : td){
//            t.join(); // 全部线程执行完成
        }

    }

    public static void main(String[] args) throws InterruptedException {

        AtomicTest at = new AtomicTest();
        at.test();
        System.out.println("a=" + at.a);

        System.out.println("b=" + at.b.get());
        System.out.println(Thread.currentThread().getName());

    }
}
