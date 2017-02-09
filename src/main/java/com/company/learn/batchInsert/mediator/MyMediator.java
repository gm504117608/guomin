package com.company.learn.batchInsert.mediator;

import com.company.learn.batchInsert.chainOfResponsibility.impl.MyHandler;
import com.company.learn.batchInsert.chainOfResponsibility.impl.MyHandler7;
import com.company.learn.batchInsert.chainOfResponsibility.impl.MyHandler8;
import com.company.learn.batchInsert.chainOfResponsibility.impl.MyHandler9;

import java.math.BigDecimal;

/**
 * 中介者模式处理价格打折
 */
public class MyMediator implements Mediator {

    private MyHandler my = null;
    private MyHandler9 my9 = null;
    private MyHandler8 my8 = null;
    private MyHandler7 my7 = null;

    @Override
    public void createMediator() {
        my = new MyHandler();
        my9 = new MyHandler9();
        my8 = new MyHandler8();
        my7 = new MyHandler7();

        my.setHandler(my9);
        my9.setHandler(my8);
        my8.setHandler(my7);
    }


    @Override
    public BigDecimal workAll(BigDecimal price) {
        return my.calculatorPrice(price);
    }
}
