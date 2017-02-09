package com.company.learn.batchInsert.factory;

import com.company.learn.batchInsert.IBatchInsert;

/**
 * 批量插入测试数据工厂类
 */
public class BatchInsertFactory {

    /**
     * 执行插入数据的公共方法
     * @param bi
     */
    public static void insert(IBatchInsert bi){
        bi.batchInsert();
    }
}

