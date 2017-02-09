package com.company.learn.AOP;

/**
 * Created by lenovo on 2016/8/13.
 */
public interface IHello {

    /**
     * 业务处理A方法
     *
     * @param name
     */
    void sayHello(String name);

    /**
     * 业务处理B方法
     *
     * @param name
     */
    void sayGoodBye(String name);
}
