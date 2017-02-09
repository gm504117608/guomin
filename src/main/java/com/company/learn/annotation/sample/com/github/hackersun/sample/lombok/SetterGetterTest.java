package com.company.learn.annotation.sample.com.github.hackersun.sample.lombok;


import com.company.learn.annotation.annotation.com.github.hackersun.annotation.Getter;
import com.company.learn.annotation.annotation.com.github.hackersun.annotation.Setter;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/18
 */
@Getter
@Setter
public class SetterGetterTest {

    String name;

    int age;

    public static void main(String[] args) {
        SetterGetterTest sgt = new SetterGetterTest();
//        sgt.setName("abc");
//        sgt.setAge(123);
//        System.out.println("Name:" + sgt.getName());
//        System.out.println("Age:" + sgt.getAge());
    }
}
