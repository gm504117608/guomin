package com.company.user.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 任何通知（Advice）方法可以将第一个参数定义为 org.aspectj.lang.JoinPoint类型。
 * JoinPoint接口提供了一系列有用的方法，
 * 比如 getArgs() （返回方法参数）、getThis() （返回代理对象）、getTarget()
 * （返回目标）、getSignature() （返回正在被通知的方法相关信息）
 * 和 toString() （打印出正在被通知的方法的有用信息。
 */

@Aspect
@Component
public class TestAnnotationAspect {

    @Pointcut("execution(* com.company.user.service.*.*(..))")
    public void pointCutMethond(){

    }

    @Before("pointCutMethond()")
    public void beforeInform(JoinPoint pjp){
        System.out.println("前置通知");
    }

    @After("pointCutMethond()")
    public void afterInform(JoinPoint pjp){
        System.out.println("后置通知");
    }

    @AfterReturning(pointcut = "pointCutMethond()", returning = "result")
    public void afterReturningInform(JoinPoint pjp,String result){
        System.out.println("返回值后置通知" + result);
    }

    @AfterThrowing(pointcut = "pointCutMethond()", throwing = "e")
    public void afterThrowingInform(JoinPoint pjp, Exception  e){
        System.out.println("异常之后通知");
        System.out.println(e.getMessage());
    }

    @Around("pointCutMethond()")
    public Object aroundInform(JoinPoint pjp) throws Throwable {
        System.out.println("进入方法---环绕通知");
        System.out.println("method " + pjp.getTarget().getClass().getName() + "."
                + pjp.getSignature().getName() + " throw exception");
        Object o = ((ProceedingJoinPoint)pjp).proceed(); // 调用代理的方法并获取返回值
        System.out.println("退出方法---环绕通知");
        return o;
    }

}
