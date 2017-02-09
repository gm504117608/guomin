package com.company.learn.batchInsert.strategy.impl;

import com.company.learn.batchInsert.strategy.ICash;

import java.math.BigDecimal;

/**
 * 打折类现实
 */
public class CashRebate implements ICash {

    /**
     * 商品折扣率
     */
    private double discountRate = 1;

    public CashRebate(double discountRate){
        this.discountRate = discountRate;
    }

    /**
     * 参与打折商品消费费用计算
     * @param price 商品价格
     * @param num 商品数量
     * @return 消费金额
     */
    @Override
    public BigDecimal calculatorCost(BigDecimal price, int num) {
        if(discountRate == 0){ // 没有进行打折处理
            return price.multiply(new BigDecimal(num));
        }
        return price.multiply(new BigDecimal(num)).multiply(new BigDecimal(discountRate));
    }
}
