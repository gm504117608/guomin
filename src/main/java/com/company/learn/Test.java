package com.company.learn;

/**
 * Created by dell on 2016/9/27.
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        // 获取java虚拟机可用的处理器个数
//        System.out.println(Runtime.getRuntime().availableProcessors());
//
//        System.out.println("hehehda");
//        Runtime.getRuntime().exit(0);
//        System.out.println("jjjj");
//
//        // 操作系统的信息
//        System.out.println(System.getenv());
//        // jvm的信息
//        System.out.println(System.getProperties());

//        System.out.println(offer());

        threadOrder();

        // 线程安全测试
//        ReentrantLockDemo.demoLock2();
//        ReentrantLockDemo.demoLock();

    }

    /**
     * finally里面不要做逻辑处理，只是关闭资源
     *
     * @return
     */
    public static boolean offer() {
        try {
            System.out.println(11111);

            return true;
        } finally {
            System.out.println(22222);

            return false;
        }
    }

    /**
     * 线程按照顺序执行
     */
    public static void threadOrder() throws InterruptedException {
        Thread t1 = new Thread(new Runnable(){

            public void run(){
                System.out.println("t1");
                System.out.println(Thread.currentThread().getName());
            }
        });

        Thread t2 = new Thread(new Runnable(){

            public void run(){
                System.out.println("t2");
                System.out.println(Thread.currentThread().getName());
            }
        });

        Thread t3 = new Thread(new Runnable(){

            public void run(){
                System.out.println("t3");
                System.out.println(Thread.currentThread().getName());
            }
        });

        t3.start();
        //引用t3线程，等待t3线程执行完
        t3.join();
        t1.start();
        t1.join();
        t2.start();
       // t3.start(); //  同一个线程启动两次出现后面的异常：  java.lang.IllegalThreadStateException
    }
}
