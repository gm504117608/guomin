package com.company.learn.batchInsert.chainOfResponsibility.impl;

import com.company.learn.batchInsert.chainOfResponsibility.Handler;

import java.math.BigDecimal;

/**
 * 9折处理
 */
public class MyHandler9 extends AbstractHandler implements Handler {

    @Override
    public BigDecimal calculatorPrice(BigDecimal price) {
        if(price.compareTo(new BigDecimal(199)) >= 0 && price.compareTo(new BigDecimal(399)) < 0){
            System.out.println("需要打9折");
            return price.multiply(new BigDecimal(0.9));
        }
        if(null != getHandler()){
            return getHandler().calculatorPrice(price);
        }
        return null;
    }

}
