package com.company.learn.batchInsert.strategy.impl;

import com.company.learn.batchInsert.strategy.ICash;

import java.math.BigDecimal;
import java.util.List;

/**
 * 工厂模式加上策略模式实现价格优惠计算
 */
public class CashStrategy {

    private ICash cash;

    /**
     * 构造函数
     * @param cash 优惠对象
     */
    public CashStrategy(ICash cash) {
        this.cash = cash;
    }

    /**
     * 工厂模式创建对象
     * @param type 使用打折类型
     * @param discountRate 折扣率
     * @param returnMoney 立减金额
     * @param full 满多少
     * @param subtract 减多少
     */
    public CashStrategy(int type, double discountRate, BigDecimal returnMoney, BigDecimal full, BigDecimal subtract){
        switch (type) {
            case 0 :
                this.cash = new CashNormal();
                break;
            case 1 :
                this.cash = new CashRebate(discountRate);
                break;
            case 2 :
                this.cash = new CashReturn(returnMoney);
                break;
            case 3 :
                this.cash = new CashFullSubtract(full, subtract);
                break;
        }
    }

    /**
     * 商品消费费用计算
     * @param price 商品价格
     * @param num 商品数量
     * @return 消费金额
     */
    public BigDecimal calculatorCost(BigDecimal price, int num){
        return cash.calculatorCost(price, num);
    }

    /**
     * 多个商品消费费用计算
     * @param price 商品价格列表
     * @param num 商品数量列表
     * @return 消费金额
     */
    public BigDecimal calculatorCost(List<BigDecimal> price, List<Integer> num){
        BigDecimal cost = new BigDecimal(0);
        if(null == price || price.isEmpty()){
            return cost;
        }
        int size = price.size();
        for(int i=0; i<size; i++){
            cost = cost.add(calculatorCost(price.get(i), num.get(i)));
        }
        return cost;
    }
}
