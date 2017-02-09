package com.company.learn.batchInsert.observer;

import com.company.learn.batchInsert.impl.BatchInsertDefault;
import com.company.learn.batchInsert.impl.BatchInsertExecuteBatch;
import com.company.learn.batchInsert.impl.BatchInsertLoadCommit;
import com.company.learn.batchInsert.impl.BatchInsertNoCommit;

/**
 * 观察者模式测试数据库批量插入性能
 */
public class Test {
    public static void main(String[] args) {
        Subject ms = new MySubject();
        ms.add(new BatchInsertDefault());
        ms.add(new BatchInsertNoCommit());
        ms.add(new BatchInsertExecuteBatch());
        ms.add(new BatchInsertLoadCommit());

        ms.operation();
    }
}
