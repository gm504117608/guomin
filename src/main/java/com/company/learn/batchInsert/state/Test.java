package com.company.learn.batchInsert.state;

/**
 * 状态模式 测试批量插入数据库的性能
 */
public class Test {
    public static void main(String[] args){
        State s = new State("0");
        s.batchInsert();

        State s1 = new State("1");
        s1.batchInsert();

        State s2 = new State("2");
        s2.batchInsert();

        State s3 = new State("3");
        s3.batchInsert();
    }
}
