package com.company.learn.batchInsert.chainOfResponsibility.impl;

import com.company.learn.batchInsert.chainOfResponsibility.Handler;

import java.math.BigDecimal;

/**
 * 7折处理
 */
public class MyHandler7 extends AbstractHandler implements Handler {

    @Override
    public BigDecimal calculatorPrice(BigDecimal price) {
        if(price.compareTo(new BigDecimal(599)) >= 0){
            System.out.println("需要打7折");
            return price.multiply(new BigDecimal(0.7));
        }
        if(null != getHandler()){
            return getHandler().calculatorPrice(price);
        }
        return null;
    }

}
