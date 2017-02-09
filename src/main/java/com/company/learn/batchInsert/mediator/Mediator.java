package com.company.learn.batchInsert.mediator;

import java.math.BigDecimal;

/**
 * 中介者模式处理价格打折计算
 * 需求：
 * 在一个购物商城，在五一做了一个活动，所以图书类商品根据购买的金额依次做出以下折扣方案，
 * 1、购买满199元,打9折
 * 2、购买满399元,打8折
 * 3、购买满599元以上,打7折；
 *
 */
public interface Mediator {
    /**
     * 获取中介者关联的类
     */
    public void createMediator();

    /**
     * 执行关联类的方法
     */
    public BigDecimal workAll(BigDecimal price);
}
