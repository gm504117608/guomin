package com.company.learn.AOP.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类执行方法的公共入口类
 * Created by dell on 2017/3/9.
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target){
        this.target = target;
    }

    /**
     * @param proxy   指代我们所代理的那个真实对象 (即本示例中的 HelloWorld接口)
     * @param method  指代我们调用真实对象的某个方法的Method对象 (即本示例中的 HelloWorld接口 的 sayHello方法)
     * @param args    指代调用真实对象方法时的参数 (即本示例中的 HelloWorld接口 的 sayHello方法的参数)
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invocation");
        Object retVal = method.invoke(target, args);
        System.out.println("After invocation");
        return retVal;
    }
}
