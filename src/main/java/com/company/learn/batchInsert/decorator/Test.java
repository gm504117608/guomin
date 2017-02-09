package com.company.learn.batchInsert.decorator;

import com.company.learn.batchInsert.IBatchInsert;
import com.company.learn.batchInsert.impl.BatchInsertDefault;
import com.company.learn.batchInsert.impl.BatchInsertExecuteBatch;
import com.company.learn.batchInsert.impl.BatchInsertLoadCommit;
import com.company.learn.batchInsert.impl.BatchInsertNoCommit;

/**
 * 装饰模式测试数据库批量数据插入性能测试
 */
public class Test {
    public static void main(String[] args){

        IBatchInsert Decorator1 = new Decorator(new BatchInsertDefault());
        Decorator1.batchInsert();

        IBatchInsert Decorator2 = new Decorator(new BatchInsertNoCommit());
        Decorator2.batchInsert();

        IBatchInsert Decorator3 = new Decorator(new BatchInsertExecuteBatch());
        Decorator3.batchInsert();

        IBatchInsert Decorator4 = new Decorator(new BatchInsertLoadCommit());
        Decorator4.batchInsert();

    }
}
