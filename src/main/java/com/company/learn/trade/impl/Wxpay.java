package com.company.learn.trade.impl;

import com.company.learn.trade.PaymentAbstract;

/**
 * 微信支付实现类
 */
public class Wxpay extends PaymentAbstract {

    /**
     * 微信支付
     * @return
     */
    @Override
    public Object pay() {
        System.out.println("wxpay");
        return null;
    }

    /**
     * 微信支付完成之后退款
     * @return
     */
    @Override
    public Object refund() {
        return null;
    }
}
