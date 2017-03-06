package com.company.learn.trade.impl;

import com.company.learn.trade.PaymentAbstract;

/**
 * 支付宝支付实现类
 */
public class Alipay extends PaymentAbstract {

    /**
     * 支付宝支付
     * @return
     */
    @Override
    public Object pay() {
        System.out.println("alipay");
        return null;
    }

    /**
     * 支付宝支付完成之后退款
     * @return
     */
    @Override
    public Object refund() {
        return null;
    }
}
