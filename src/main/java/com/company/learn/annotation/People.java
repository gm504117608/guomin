package com.company.learn.annotation;

import java.lang.annotation.*;

/**
 * Created by dell on 2016/12/22.
 *
 *  @Target?  — —注解用于什么地方
 *      TYPE,  //给类（型）注解
 *      FIELD, //给字段注解，不要忘了，字段可以是对象
 *      METHOD, //给方法注解
 *      PARAMETER, //给参数注解
 *      CONSTRUCTOR, //给构造方法注解
 *      LOCAL_VARIABLE, //给局部变量注解
 *      ANNOTATION_TYPE,//给注解注解（这貌似把自己不当类来看）
 *      PACKAGE, //给包注解
 *  @Retention — —注解运行状态
 *      SOURCE, //源码状态运行，
 *      CLASS, //编译类文件时运行
 *      RUNTIME //运行时运行
 *  @Documented — — 生成说明文档，添加类的解释
 *  @Inherited — —允许子类继承父类中的注解。
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface People {

    String name() default "小灰灰";
}
