package com.company.learn.batchInsert.facade;

import com.company.learn.batchInsert.impl.BatchInsertDefault;
import com.company.learn.batchInsert.impl.BatchInsertExecuteBatch;
import com.company.learn.batchInsert.impl.BatchInsertLoadCommit;
import com.company.learn.batchInsert.impl.BatchInsertNoCommit;

/**
 * Created by dell on 2017/1/6.
 */
public class FacadeBatchInsert {

    /**
     * 执行插入数据的公共方法
     */
    public void insert(){

        new BatchInsertDefault().batchInsert();
        new BatchInsertNoCommit().batchInsert();
        new BatchInsertExecuteBatch().batchInsert();
        new BatchInsertLoadCommit().batchInsert();

    }
}
