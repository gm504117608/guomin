package com.company.learn.batchInsert.chainOfResponsibility.impl;

import com.company.learn.batchInsert.chainOfResponsibility.Handler;

import java.math.BigDecimal;

/**
 * 8折处理
 */
public class MyHandler8 extends AbstractHandler implements Handler {

    @Override
    public BigDecimal calculatorPrice(BigDecimal price) {
        if(price.compareTo(new BigDecimal(399)) >= 0 && price.compareTo(new BigDecimal(599)) < 0){
            System.out.println("需要打8折");
            return price.multiply(new BigDecimal(0.8));
        }
        if(null != getHandler()){
            return getHandler().calculatorPrice(price);
        }
        return null;
    }

}
