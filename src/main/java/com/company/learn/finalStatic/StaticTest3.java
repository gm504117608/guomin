package com.company.learn.finalStatic;

/**
 * Created by dell on 2017/1/3.
 */
public class StaticTest3 {
    private static int a;
    private int b;
    // 多个static按照定义的顺序执行
    static {
        StaticTest3.a = 3;
        System.out.println(a);
        StaticTest3 t = new StaticTest3();
        t.f();
        t.b = 1000;
        System.out.println(t.b);
    }
    static {
        StaticTest3.a = 4;
        System.out.println(a);
    }
    static {
        StaticTest3.a = 5;
        System.out.println(a);
    }
    public void f() {
        System.out.println("hhahhahah");
    }
    public static void main(String[] args) {}
}
