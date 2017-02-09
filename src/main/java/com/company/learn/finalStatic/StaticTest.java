package com.company.learn.finalStatic;

/**
 * Created by dell on 2017/1/3.
 */
public class StaticTest {
    private static String name;
    private static Integer money;
    private String phone;
    private static int age;
    private static double grade;

    public StaticTest(String name, String phone, int age, double grade, Integer money){
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.grade = grade;
        this.money = money;
    }
    public static void main(String[] args) {
        System.out.println(StaticTest.name); // 通过类名直接访问静态变量
        System.out.println(StaticTest.age); // 通过类名直接访问静态变量
//        System.out.println(StaticTest.phone); // 不能通过类名访问，因为不是静态变量
        StaticTest t = new StaticTest("gg", "123", 2, 12.03, 120);
        StaticTest t2 = new StaticTest("hh", "123456", 12, 1.09, 130);

        System.out.println(t.phone);
        System.out.println(t.name);
        System.out.println(t2.name);
        System.out.println(t.money);
        System.out.println(t2.money);
        System.out.println(t.age); // 输出结果12
        System.out.println(t.grade); // 输出结果1.09
        System.out.println(t2.phone);
        System.out.println(t2.age); // 输出结果12  因为基本数据类型存入的值和地址是一样的，再加上是静态变量，共享同一个变量，所以赋值会覆盖值
        System.out.println(t2.grade); // 输出结果1.09  因为基本数据类型存入的值和地址是一样的，再加上是静态变量，共享同一个变量，所以赋值会覆盖值
    }
}
