package com.company.learn.finalStatic;

/**
 * Created by dell on 2017/1/3.
 */
public class StaticTest2 {

    private static String name;
    private String age;

    public static void test(String name, String age){
        StaticTest2.name = name;
        age = age; // static方法内不能对非static的方法或变量进行操作，不然会出菊花，这样导致age赋值不成功
    }

    public static void main(String[] args) {
        StaticTest2.test("fff", "12");
        StaticTest2 t = new StaticTest2();

        System.out.println(t.name);
        System.out.println(t.age);
    }
}
