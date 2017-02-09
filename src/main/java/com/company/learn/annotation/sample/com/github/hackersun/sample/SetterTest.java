package com.company.learn.annotation.sample.com.github.hackersun.sample;


import com.company.learn.annotation.annotation.com.github.hackersun.annotation.Getter;
import com.company.learn.annotation.annotation.com.github.hackersun.annotation.Setter;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/19
 */
@Setter
@Getter
public class SetterTest {
    String name;

    MyType age;

    int gender;

    String a;

    SetterTest(){
        name = "jhon";
        age = new MyType();
        gender = 1;
        a = "test";
    }

    public static void main(String[] args) {
        SetterTest st = new SetterTest();
        System.out.println("----");
//        System.out.println(st.getName());
//        System.out.println(st.getAge());
//        System.out.println(st.getGender());
//        System.out.println(st.getAbcddd());
    }

    public class MyType{

    }

}
