package com.company.learn.dataSourcePool;

import com.company.learn.dataSourcePool.impl.ConnectionPoolManager;

/**
 *  http://greemranqq.iteye.com/blog/1969273
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        // 初始化连接池
        Thread t = init();
        t.start();
        t.join();

        ThreadConnection a = new ThreadConnection("testPool2");
        ThreadConnection b = new ThreadConnection("testPool1");
        ThreadConnection c = new ThreadConnection("testPool2");
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        Thread t3 = new Thread(c);


        // 设置优先级，先让初始化执行，模拟 线程池 先启动
        // 这里仅仅表面控制了，因为即使t 线程先启动，也不能保证pool 初始化完成，为了简单模拟，这里先这样写了
        t1.setPriority(10);
        t2.setPriority(10);
        t3.setPriority(10);
        t1.start();
        t2.start();
        t3.start();

        System.out.println("线程A-> " + a.getConnection());
        System.out.println("线程B-> " + b.getConnection());
        System.out.println("线程C-> " + c.getConnection());

        Thread current = Thread.currentThread();
        System.out.println("当前线程：------");
        System.out.println(current.getPriority());
        System.out.println(current.getName());
        System.out.println(current.activeCount());
        System.out.println(current.getId());
        System.out.println(current.getThreadGroup());
        System.out.println(current.getStackTrace());
        System.out.println(current.hashCode());
        System.out.println(current.toString());
        System.out.println("当前线程：------");

        System.out.println("线程A-> " + a.getCurrentConnection());
        System.out.println("线程B-> " + b.getCurrentConnection());
        System.out.println("线程C-> " + c.getCurrentConnection());


//        // 单例获取连接池管理对象
//        ConnectionPoolManager cpm = ConnectionPoolManager.getInstance();
//        Connection conn2 = cpm.getConnection("testPool2");
//        Connection conn = cpm.getConnection("testPool1");
//        Connection conn3 = cpm.getCurrentConnection("testPool2");
//        Connection conn4 = cpm.getCurrentConnection("testPool1");
//        IConnectionPool pool = cpm.getPool("testPool1");
//        IConnectionPool pool2 = cpm.getPool("testPool2");
//        cpm.close("testPool1",conn);
//        cpm.close("testPool2",conn2);
//        cpm.destroy("testPool1");
//        cpm.destroy("testPool2");


    }

    // 初始化
    public static Thread init() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectionPoolManager pool = initPool();
            }
        });
        return t;
    }

    public static ConnectionPoolManager initPool() {
        return ConnectionPoolManager.getInstance();
    }

}
