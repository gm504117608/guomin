package com.company.learn.batchInsert.bridge;

import com.company.learn.batchInsert.IBatchInsert;
import com.company.learn.batchInsert.impl.BatchInsertDefault;
import com.company.learn.batchInsert.impl.BatchInsertExecuteBatch;
import com.company.learn.batchInsert.impl.BatchInsertLoadCommit;
import com.company.learn.batchInsert.impl.BatchInsertNoCommit;

/**
 * Created by dell on 2016/12/12.
 */
public class Test {
    public static void main(String[] args){

        Bridge bridge = new MyBridge();

        Long start = System.currentTimeMillis();
        //调用数据库默认的提交方式插入数据
        IBatchInsert bi1 = new BatchInsertDefault();
        bridge.setBatchInsert(bi1);
        bridge.batchInsert();
        Long end = System.currentTimeMillis();
        System.out.println("=========BatchInsertDefault执行时间：===" + (end-start) + "毫秒");

        Long start2 = System.currentTimeMillis();
        //调用数据库手动提交的方式插入数据
        IBatchInsert bi2 = new BatchInsertNoCommit();
        bridge.setBatchInsert(bi2);
        bridge.batchInsert();
        Long end2 = System.currentTimeMillis();
        System.out.println("=========BatchInsertNoCommit执行时间：===" + (end2-start2) + "毫秒");


        Long start3 = System.currentTimeMillis();
        //调用数据库批量提交执行方式插入数据
        IBatchInsert bi3 = new BatchInsertExecuteBatch();
        bridge.setBatchInsert(bi3);
        bridge.batchInsert();
        Long end3 = System.currentTimeMillis();
        System.out.println("=========BatchInsertExecuteBatch执行时间：===" + (end3-start3) + "毫秒");


        Long start4 = System.currentTimeMillis();
        //调用数据库读取文件的方式方式插入数据
        IBatchInsert bi4 = new BatchInsertLoadCommit();
        bridge.setBatchInsert(bi4);
        bridge.batchInsert();
        Long end4 = System.currentTimeMillis();
        System.out.println("=========BatchInsertLoadCommit执行时间：===" + (end4-start4) + "毫秒");

    }

}
