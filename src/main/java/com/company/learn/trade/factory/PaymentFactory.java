package com.company.learn.trade.factory;

import com.company.learn.trade.Payment;
import com.company.learn.trade.impl.Alipay;
import com.company.learn.trade.impl.PointPay;
import com.company.learn.trade.impl.PrepayCardPay;
import com.company.learn.trade.impl.Wxpay;

/**
 * 支付工厂类
 */
public class PaymentFactory {

    /**
     * 产生阿里支付实例
     * @return
     */
    public static Payment productAlipay(){
        return new Alipay();
    }

    /**
     * 产生微信支付实例
     * @return
     */
    public static Payment productWxpay(){
        return new Wxpay();
    }

    /**
     * 产生积分支付实例
     * @return
     */
    public static Payment productPointPay(){
        return new PointPay();
    }

    /**
     * 产生储值卡支付实例
     * @return
     */
    public static Payment productPrepayCardPay(){
        return new PrepayCardPay();
    }
}
