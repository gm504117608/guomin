package com.company.learn.batchInsert.bridge;

/**
 * Created by dell on 2016/12/12.
 */
public class MyBridge extends Bridge {

    @Override
    public int batchInsert(){
        return getBatchInsert().batchInsert();
    }
}
