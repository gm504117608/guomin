package com.company.learn.batchInsert.decorator;

import com.company.learn.batchInsert.IBatchInsert;

/**
 * 装饰模式实现数据库批量插入数据性能测试
 *
 * 装饰类
 */
public class Decorator implements IBatchInsert {

    private IBatchInsert iBatchInsert;

    public Decorator(IBatchInsert iBatchInsert){
        super();
        this.iBatchInsert = iBatchInsert;
    }

    /**
     * 在装饰方法的前后做运行时间的统计计算
     * @return
     */
    @Override
    public int batchInsert() {
        Long start = System.currentTimeMillis();

        iBatchInsert.batchInsert(); // 被装饰方法的执行

        Long end = System.currentTimeMillis();
        System.out.println(iBatchInsert.getClass().getName() + "=========执行时间：===" + (end-start) + "毫秒");

        return 0;
    }
}
