package com.company.learn.AOP;

import java.lang.reflect.Method;

/**
 * Created by lenovo on 2016/8/13.
 */
public class Operation implements IOperation {

    public void start(Method method) {
        System.out.println("testing " + method.getName() + " Method Start .");
    }

    public void end(Method method) {
        System.out.println("testing " + method.getName() + " Method end .");
    }
}
