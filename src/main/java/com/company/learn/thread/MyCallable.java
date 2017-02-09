package com.company.learn.thread;

import java.util.concurrent.Callable;

/**
 * 使用Callable和Future接口创建线程
 *
 * 具体是创建Callable接口的实现类，并实现call()方法。
 * 并使用FutureTask类来包装Callable实现类的对象，
 * 且以此FutureTask对象作为Thread对象的target来创建线程。
 *
 */
public class MyCallable implements Callable<Integer> {

    // 与run()方法不同的是，call()方法具有返回值
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i=0; i<100; i++){
            System.out.println(Thread.currentThread().getName() + " === i= " + i);
            sum = sum + i;
        }
        return sum;
    }
}
