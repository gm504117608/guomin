package com.company.learn.batchInsert;


/**
 * 使用工厂模式来处理批量往数据库加入数据性能测试
 */
public interface IBatchInsert {

    /**
     * 批量插入数据测试
     * @return
     */
    public int batchInsert();

//    /**
//     * 批量插入入口方法
//     * @param bi
//     */
//    public default void insert(IBatchInsert bi){
//        bi.batchInsert();
//    }
}
