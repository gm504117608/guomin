package com.company.learn.batchInsert.factory;

import com.company.learn.batchInsert.impl.BatchInsertDefault;
import com.company.learn.batchInsert.impl.BatchInsertExecuteBatch;
import com.company.learn.batchInsert.impl.BatchInsertLoadCommit;
import com.company.learn.batchInsert.impl.BatchInsertNoCommit;

/**
 * Created by dell on 2016/12/8.
 */
public class Test {


    public static void main(String[] args){

        Long start = System.currentTimeMillis();
        BatchInsertFactory.insert(new BatchInsertDefault());
        Long end = System.currentTimeMillis();
        System.out.println("=========BatchInsertDefault执行时间：===" + (end-start) + "毫秒");

        Long start2 = System.currentTimeMillis();
        BatchInsertFactory.insert(new BatchInsertNoCommit());
        Long end2 = System.currentTimeMillis();
        System.out.println("=========BatchInsertNoCommit执行时间：===" + (end2-start2) + "毫秒");

        Long start3 = System.currentTimeMillis();
        BatchInsertFactory.insert(new BatchInsertExecuteBatch());
        Long end3 = System.currentTimeMillis();
        System.out.println("=========BatchInsertExecuteBatch执行时间：===" + (end3-start3) + "毫秒");

        Long start4 = System.currentTimeMillis();
        BatchInsertFactory.insert(new BatchInsertLoadCommit());
        Long end4 = System.currentTimeMillis();
        System.out.println("=========BatchInsertLoadCommit执行时间：===" + (end4-start4) + "毫秒");

    }
}
