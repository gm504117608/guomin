package com.company.learn.batchInsert.strategy.impl;

import com.company.learn.batchInsert.strategy.ICash;

import java.math.BigDecimal;

/**
 * 返现立减类现实
 */
public class CashReturn implements ICash {

    /**
     * 返现金额
     */
    private BigDecimal returnMoney = new BigDecimal(0);

    public CashReturn(BigDecimal returnMoney){
        this.returnMoney = returnMoney;
    }

    /**
     * 参与立减商品消费费用计算
     * @param price 商品价格
     * @param num 商品数量
     * @return 消费金额
     */
    @Override
    public BigDecimal calculatorCost(BigDecimal price, int num) {
        BigDecimal cost = price.multiply(new BigDecimal(num));
        if(cost.compareTo(returnMoney) <= 0){ // 如果立减的金额大于消费金额则不进行立减操作
            return cost;
        }
        return cost.subtract(returnMoney);
    }
}
