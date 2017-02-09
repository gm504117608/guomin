package com.company.learn.batchInsert.chainOfResponsibility.impl;

import com.company.learn.batchInsert.chainOfResponsibility.Handler;

import java.math.BigDecimal;

/**
 * 原价处理类
 */
public class MyHandler extends AbstractHandler implements Handler {

    @Override
    public BigDecimal calculatorPrice(BigDecimal price) {
        if(price.compareTo(new BigDecimal(199)) < 0){
            System.out.println("不需要打折");
            return price;
        }
        if(null != getHandler()){
            return getHandler().calculatorPrice(price);
        }
        return null;
    }

}
