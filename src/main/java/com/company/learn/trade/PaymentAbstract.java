package com.company.learn.trade;

/**
 * Created by dell on 2017/3/3.
 */
public abstract class PaymentAbstract implements Payment {
    /**
     * 继承PaymentAbstract类的类需要重写支付方法
     * @return
     */
    @Override
    public Object pay() {
        return null;
    }

    /**
     * 支付完成之后退款
     * @return
     */
    public abstract Object refund();
}
