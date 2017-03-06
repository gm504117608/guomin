package com.company.learn.trade.impl;

import com.company.learn.trade.PaymentAbstract;

/**
 * 积分支付实现类
 */
public class PointPay extends PaymentAbstract {

    /**
     * 积分支付
     * @return
     */
    @Override
    public Object pay() {
        System.out.println("pointPay");
        return null;
    }

    /**
     * 积分支付完成之后退款
     * @return
     */
    @Override
    public Object refund() {
        return null;
    }
}
