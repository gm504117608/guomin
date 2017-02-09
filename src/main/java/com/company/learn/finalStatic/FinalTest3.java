package com.company.learn.finalStatic;

/**
 * 用final修饰的成员变量表示常量，值一旦给定就无法改变！
 * final修饰的变量有三种：静态变量、实例变量和局部变量，分别表示三种类型的常量。
 */
public class FinalTest3 {
    private final String name = "guomin";
    private final int age = 12;
    public final boolean flag = true;
    public final int grade; // final空白,必须在初始化对象的时候赋初值

    public final Integer i = new Integer(123);

    public FinalTest3(int grade) {
        this.grade = grade;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        FinalTest3 t = new FinalTest3(2);
//        t.flag = false;  // 出错,final变量的值一旦给定就无法改变
        //        t.name = "23123"; // 出错,final变量的值一旦给定就无法改变
//        t.i = new Integer(32323); // 出错,final变量的值一旦给定就无法改变
        System.out.println(t.flag);
        System.out.println(t.name);
        System.out.println(t.age);
        System.out.println(t.grade); // final空白变量E依据对象的不同而不同
//        t.grade = 34; // 出错,final变量的值一旦给定就无法改变

        FinalTest3 t1 = new FinalTest3(3);
        System.out.println(t1.grade); // final空白变量E依据对象的不同而不同
    }
    public void test() {
        final int a;     // final空白,在需要的时候才赋值
        final int b = 4;    // 局部常量--final用于局部变量的情形
        final int c;    // final空白,一直没有给赋值.
        a = 3;
        //a=4;    出错,已经给赋过值了.
        //b=2; 出错,已经给赋过值了.
    }

}
