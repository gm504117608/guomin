package com.company.learn.AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk跟我们提供了一个API   java.lang.reflect.InvocationHandler的接口.
 * 这个接口可以让我们在JVM调用某个类的方法时动态的为些方法做些什么事
 */
public class DynaProxyHello implements InvocationHandler {

    /**
     * 操作者
     */
    private Object proxy;

    /**
     * 要处理的对象(也就是我们要在方法的前后加上业务逻辑的对象,如例子中的Hello)
     */
    private Object delegate;

    /**
     * 动态生成方法被处理过后的对象 (写法固定)
     *
     * @param delegate
     * @param proxy
     * @return
     */
    public Object bind(Object delegate, Object proxy){
        this.proxy = proxy;
        this.delegate = delegate;
        return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(),
                this.delegate.getClass().getInterfaces(), this);
    }

    /**
     * 动态生成方法被处理过后的对象 (写法固定)
     * @param delegate
     * @return
     */
    public Object bind(Object delegate){
        this.delegate = delegate;
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                this.delegate.getClass().getInterfaces(), this);
    }

    /**
     * 要处理的对象中的每个方法会被此方法送去JVM调用,也就是说,要处理的对象的方法只能通过此方法调用
     * 此方法是动态的,不是手动调用的
     *  proxy:　　  指代我们所代理的那个真实对象
     *  method:　 　指代的是我们所要调用真实对象的某个方法的Method对象
     *  args:　　   指代的是调用真实对象某个方法时接受的参数
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try{
            //执行原来的方法之前记录日志
            //反射得到操作者的实例
            Class clazz = this.proxy.getClass();
            //反射得到操作者的Start方法
            Method start = clazz.getDeclaredMethod("start", new Class[]{Method.class});
            //反射执行start方法
            start.invoke(this.proxy, new Object[]{method});

            //JVM通过这条语句执行原来的方法(反射机制)
            result = method.invoke(this.delegate, args);

            //执行原来的方法之后记录日志
            //反射得到操作者的end方法
            Method end = clazz.getDeclaredMethod("end", new Class[] { Method.class });
            //反射执行end方法
            end.invoke(this.proxy, new Object[] { method });
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
