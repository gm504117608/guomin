package com.company.learn.batchInsert.strategy;

import java.math.BigDecimal;

/**
 * 计算费用接口
 *
 * 策略模式现实商品打折方式
 * 折扣
 * 返现
 * 立减
 */
public interface ICash {

    /**
     * 商品消费费用计算
     * @param price 商品价格
     * @param num 商品数量
     * @return 消费金额
     */
    BigDecimal calculatorCost(BigDecimal price, int num);
}
