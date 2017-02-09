package com.company.learn.finalStatic;

/**
 * final类不能被继承，因此final类的成员方法没有机会被覆盖，默认都是final的。
 * 在设计类时候，如果这个类不需要有子类，
 * 类的实现细节不允许改变，并且确信这个类不会载被扩展，那么就设计为final类。
 */
public final class FinalTest {

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void test(String age){
        this.age = age;
        System.out.println(name + " : " + this.age);
    }
}
