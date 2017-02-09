package com.company.learn.batchInsert.mediator;

import java.math.BigDecimal;

/**
 * 中介者模式实现价格打折
 */
public class Test {
    public static void main(String[] args){
        Mediator m = new MyMediator();
        m.createMediator();
        System.out.println(m.workAll(new BigDecimal(1000)));
    }
}
