package com.company.learn.batchInsert.facade;

/**
 * 外观模式（门面模式）处理批量插入数据库数据性能测试
 */
public class Test {

    public static void main(String[] args) {

        FacadeBatchInsert fbi = new FacadeBatchInsert();

        fbi.insert();
    }
}
