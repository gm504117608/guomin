package com.company.learn.trade.impl;

import com.company.learn.trade.PaymentAbstract;

/**
 * 储值卡支付实现类
 */
public class PrepayCardPay extends PaymentAbstract {

    /**
     * 储值卡支付
     * @return
     */
    @Override
    public Object pay() {
        System.out.println("PrepayCardPay");
        return null;
    }

    /**
     * 储值卡支付完成之后退款
     * @return
     */
    @Override
    public Object refund() {
        return null;
    }
}
