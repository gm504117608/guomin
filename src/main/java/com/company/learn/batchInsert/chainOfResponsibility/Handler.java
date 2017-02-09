package com.company.learn.batchInsert.chainOfResponsibility;

import java.math.BigDecimal;

/**
 * 责任链模式处理价格打折计算
 * 需求：
 * 在一个购物商城，在五一做了一个活动，所以图书类商品根据购买的金额依次做出以下折扣方案，
 * 1、购买满199元,打9折
 * 2、购买满399元,打8折
 * 3、购买满599元以上,打7折；
 *
 */
public interface Handler {

    /**
     * 计算打折之后的价格
     * @return
     */
    public BigDecimal calculatorPrice(BigDecimal price);

}
