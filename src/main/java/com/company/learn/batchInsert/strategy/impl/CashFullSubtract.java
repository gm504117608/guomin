package com.company.learn.batchInsert.strategy.impl;

import com.company.learn.batchInsert.strategy.ICash;

import java.math.BigDecimal;

/**
 * 满多少减多少钱活动
 */
public class CashFullSubtract implements ICash {

    /**
     * 满多少钱
     */
    private BigDecimal full;
    /**
     * 减多少钱
     */
    private BigDecimal subtract;

    public CashFullSubtract(BigDecimal full, BigDecimal subtract){
        this.full = full;
        this.subtract = subtract;
    }

    /**
     * 计算商品满多少减多少之后的价格
     * @param price 商品价格
     * @param num 商品数量
     * @return 消费金额
     */
    @Override
    public BigDecimal calculatorCost(BigDecimal price, int num) {
        BigDecimal cost = price.multiply(new BigDecimal(num));
        if(cost.compareTo(full) > 0){
            return cost.subtract(subtract);
        }
        return cost;
    }
}
