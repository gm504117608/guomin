package com.company.learn.thread;

/**
 * Created by dell on 2016/12/29.
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        // 通过继承Thread创建线程
//        Thread mt = new MyThread();
//        mt.start();
//
//        System.out.println("=================");
//
//        // 通过实现Runnable创建线程
//        Runnable mr = new MyRunnable();
//        Thread t = new Thread(mr);
//        t.start();
//
//        System.out.println("=================");
//
//        // 使用Callable和Future接口创建线程
//        Callable<Integer> mc = new MyCallable(); // 创建MyCallable对象
//        FutureTask<Integer> ft = new FutureTask<Integer>(mc); // 使用FutureTask来包装MyCallable对象
//        Thread t2 = new Thread(ft);   //FutureTask对象作为Thread对象的target创建新的线程
//        t2.start();                   //线程进入到就绪状态
//        int sum = ft.get();            //取得新创建的新线程中的call()方法返回的结果 他会阻塞等待线程执行完成在取出值
//        System.out.println("sum = " + sum);

//        // 通过实现Runnable创建线程 操作运行阻塞 停止 死亡状态
//        MyRunnable2 mr = new MyRunnable2();
//        Thread t = new Thread(mr);
//        for (int i = 0; i < 100; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);// main 线程
//            if (i == 30) {
//                t.start();
//            }
//            if(i == 80){
//                mr.setRunning(false);
//            }
//        }

//        // 通过实现Runnable创建线程 阻止当前执行执行：join()
//        MyRunnable mr = new MyRunnable();
//        Thread t = new Thread(mr);
//        Thread t2 = new Thread(mr);
//        for (int i = 0; i < 50; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);// main 线程
//            if (i == 30) {
//                t.start();
//                t.join();
//            }
//            if (i == 10) {
//                t2.start();
//                t2.join();
//            }
//        }

        // 通过实现Runnable创建线程 线程睡眠：sleep()
//        MyRunnable mr = new MyRunnable();
//        Thread t = new Thread(mr);
//        Thread t2 = new Thread(mr);
//        for (int i = 0; i < 50; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);// main 线程
//            if (i == 30) {
//                t.start();
//                t2.start();
//                t.sleep(10000);
//            }
//        }

        // 通过实现Runnable创建线程 后台线程（Daemon Thread）设置线程为后台线程
//        MyRunnable mr = new MyRunnable();
//        Thread t = new Thread(mr);
//        for (int i = 0; i < 50; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);// main 线程
//            if (i == 30) {
//                t.setDaemon(true); // 设置线程为后台线程
//                t.start();
//                t.join();
//            }
//        }

        // 通过实现Runnable创建线程 设置线程的优先级：setPriority()
//        MyRunnable mr = new MyRunnable();
//        Thread t = new Thread(mr);
//        for (int i = 0; i < 50; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);// main 线程
//            if (i == 30) {
//                t.setPriority(Thread.MAX_PRIORITY); // 设置线程的优先级
//                t.start();
//            }
//        }

        // 通过实现Runnable创建线程 线程让步：yield()
        MyRunnable mr = new MyRunnable();
        Thread t = new Thread(mr);
        t.setPriority(Thread.MAX_PRIORITY);
        Thread t2 = new Thread(mr);
        t2.setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);// main 线程
            if (i == 10) {
                t.start();
                t2.start();
                t.yield();
            }
        }


    }
}
