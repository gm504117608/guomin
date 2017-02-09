package com.company.learn.batchInsert.observer;

import com.company.learn.batchInsert.IBatchInsert;

/**
 * 观察者模式测试数据库批量插入性能
 */
public interface Subject {

    /**
     * 增加插入数据库测试类
     * @param bi
     */
    public void add(IBatchInsert bi);

    /**
     * 除去插入数据库测试类
     * @param bi
     */
    public void remove(IBatchInsert bi);

    /**
     * 通知所有的观察者
     */
    public void notifyObservers();

    /**
     * 操作数据库批量插入
     */
    public void operation();
}
