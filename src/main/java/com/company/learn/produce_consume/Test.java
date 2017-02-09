package com.company.learn.produce_consume;

/**
 * 生产者、消费者模式设计测试
 */
public class Test {

    public static void main(String[] args){
        // 仓库对象
        Storage storage = new Storage();

        // 生产者对象
        Produce p1 = new Produce(storage);
//        Produce p2 = new Produce(storage);
//        Produce p3 = new Produce(storage);
//        Produce p4 = new Produce(storage);
//        Produce p5 = new Produce(storage);
//        Produce p6 = new Produce(storage);
//        Produce p7 = new Produce(storage);

        // 消费者对象
        Consume c1 = new Consume(storage);
        Consume c2 = new Consume(storage);
        Consume c3 = new Consume(storage);

        // 设置生产者产品生产数量
        p1.setNum(80);
//        p2.setNum(10);
//        p3.setNum(10);
//        p4.setNum(10);
//        p5.setNum(10);
//        p6.setNum(10);
//        p7.setNum(80);

        // 设置消费者产品消费数量
        c1.setNum(30);
        c2.setNum(20);
        c3.setNum(10);

        // 线程开始执行
        c1.start();
        c2.start();
        c3.start();
        p1.start();
//        p2.start();
//        p3.start();
//        p4.start();
//        p5.start();
//        p6.start();
//        p7.start();
    }

}
