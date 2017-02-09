package com.company.learn.batchInsert.state;

import com.company.learn.batchInsert.impl.BatchInsertDefault;
import com.company.learn.batchInsert.impl.BatchInsertExecuteBatch;
import com.company.learn.batchInsert.impl.BatchInsertLoadCommit;
import com.company.learn.batchInsert.impl.BatchInsertNoCommit;

/**
 * 状态模式
 */
public class State {

    private String state; // 标志不同的状态

    public State(String state){
        this.state = state;
    }

    public void batchInsert() {
        switch (state){
            case "0" :
                new BatchInsertDefault().batchInsert();
                break;
            case "1" :
                new BatchInsertExecuteBatch().batchInsert();
                break;
            case "2" :
                new BatchInsertLoadCommit().batchInsert();
                break;
            case "3" :
                new BatchInsertNoCommit().batchInsert();
                break;
        }
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
