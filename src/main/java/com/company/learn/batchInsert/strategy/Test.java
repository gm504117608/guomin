package com.company.learn.batchInsert.strategy;

import com.company.learn.batchInsert.strategy.impl.CashStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 策略模式+工厂模式 现实商品打折方式
 *
 * 折扣
 * 返现
 * 立减
 * 满多少减多少
 *
 */
public class Test {

    public static void main(String[] args){
        CashStrategy cs = new CashStrategy(0, 0.8, new BigDecimal(10), new BigDecimal(200), new BigDecimal(50));
        BigDecimal cost = cs.calculatorCost(new BigDecimal(100), 10);

        System.out.println("花费金额：" + cost);

        CashStrategy cs1 = new CashStrategy(1, 0.8, new BigDecimal(10), new BigDecimal(200), new BigDecimal(50));
        BigDecimal cost1 = cs1.calculatorCost(new BigDecimal(100), 10);

        System.out.println("花费金额：" + cost1);

        CashStrategy cs2 = new CashStrategy(2, 0.8, new BigDecimal(10), new BigDecimal(200), new BigDecimal(50));
        BigDecimal cost2 = cs2.calculatorCost(new BigDecimal(100), 10);

        System.out.println("花费金额：" + cost2);

        CashStrategy cs3 = new CashStrategy(3, 0.8, new BigDecimal(10), new BigDecimal(200), new BigDecimal(50));
        BigDecimal cost3 = cs3.calculatorCost(new BigDecimal(100), 10);

        System.out.println("花费金额：" + cost3);

        // 批量计算消费金额
        List<BigDecimal> price = new ArrayList<BigDecimal>();
        price.add(new BigDecimal(300));
        price.add(new BigDecimal(300));
        price.add(new BigDecimal(300));
        List<Integer> num = new ArrayList<Integer>();
        num.add(1);
        num.add(2);
        num.add(3);
        CashStrategy cs4 = new CashStrategy(3, 0.8, new BigDecimal(10), new BigDecimal(200), new BigDecimal(50));
        BigDecimal cost4 = cs4.calculatorCost(price, num);

        System.out.println("花费金额：" + cost4);
    }
}
