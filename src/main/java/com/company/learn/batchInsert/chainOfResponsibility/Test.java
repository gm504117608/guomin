package com.company.learn.batchInsert.chainOfResponsibility;

import com.company.learn.batchInsert.chainOfResponsibility.impl.MyHandler;
import com.company.learn.batchInsert.chainOfResponsibility.impl.MyHandler7;
import com.company.learn.batchInsert.chainOfResponsibility.impl.MyHandler8;
import com.company.learn.batchInsert.chainOfResponsibility.impl.MyHandler9;

import java.math.BigDecimal;

/**
 * 责任链模式处理价格打折业务
 */
public class Test {
    public static void main(String[] args){
        MyHandler my = new MyHandler();
        MyHandler9 my9 = new MyHandler9();
        MyHandler8 my8 = new MyHandler8();
        MyHandler7 my7 = new MyHandler7();

        my.setHandler(my9);
        my9.setHandler(my8);
        my8.setHandler(my7);

        System.out.println(my.calculatorPrice(new BigDecimal(100)));
        System.out.println(my.calculatorPrice(new BigDecimal(200)));
        System.out.println(my.calculatorPrice(new BigDecimal(300)));
        System.out.println(my.calculatorPrice(new BigDecimal(400)));
        System.out.println(my.calculatorPrice(new BigDecimal(500)));
        System.out.println(my.calculatorPrice(new BigDecimal(600)));

    }
}
