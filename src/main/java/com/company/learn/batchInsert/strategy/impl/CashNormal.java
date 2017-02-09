package com.company.learn.batchInsert.strategy.impl;

import com.company.learn.batchInsert.strategy.ICash;

import java.math.BigDecimal;

/**
 * 没有参与任何活动的消费计算
 */
public class CashNormal implements ICash {

    /**
     * 没有参与优惠方式的商品消费费用计算
     * @param price 商品价格
     * @param num 商品数量
     * @return 消费金额
     */
    @Override
    public BigDecimal calculatorCost(BigDecimal price, int num) {
        return price.multiply(new BigDecimal(num));
    }
}
