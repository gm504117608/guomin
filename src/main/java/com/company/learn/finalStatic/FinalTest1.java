package com.company.learn.finalStatic;

/**
 *  如果一个类不允许其子类覆盖某个方法，则可以把这个方法声明为final方法。
 *  使用final方法的原因有二：
 *  1、把方法锁定，防止任何继承类修改它的意义和实现。
 *  2、高效。编译器在遇到调用final方法时候会转入内嵌机制，大大提高执行效率。
 */
public class FinalTest1 {

    private String name;

    public final void setName(String name){
        this.name = name;
    }

}
