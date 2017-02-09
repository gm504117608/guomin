package com.company.learn.batchInsert.bridge;

import com.company.learn.batchInsert.IBatchInsert;

/**
 * 桥接模式现实数据库批量插入数据性能测试
 */
public abstract class Bridge {

    private IBatchInsert batchInsert;

    public int batchInsert(){
        return batchInsert.batchInsert();
    }

    public IBatchInsert getBatchInsert() {
        return batchInsert;
    }

    public void setBatchInsert(IBatchInsert batchInsert) {
        this.batchInsert = batchInsert;
    }
}
