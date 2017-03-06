package com.company.learn.trade;

import com.company.learn.trade.factory.PaymentFactory;

/**
 * Created by dell on 2017/3/3.
 */
public class Test {
    public static void main(String[] args) {
        PaymentFactory.productAlipay().pay();
        PaymentFactory.productPointPay().pay();
        PaymentFactory.productPrepayCardPay().pay();
        PaymentFactory.productWxpay().pay();
    }
}
