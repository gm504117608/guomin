package com.company.learn.finalStatic;

/**
 * Created by dell on 2017/1/3.
 */
public class FinalTest4 {

    public static void main(String[] args) {
        FinalTest4 t = new FinalTest4();
        t.test(12);
    }

    public void test(final int b) {
//        b = 10; // b 是final类型的,值不允许改变的.
        System.out.println(b);
    }
}
