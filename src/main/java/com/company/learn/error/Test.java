package com.company.learn.error;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/12/26.
 */
public class Test {

    /**
     * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     *
     * OutOfMemoryError (java heap space)   堆内存溢出
     */
    public void outOfMemory(){
        List list = new ArrayList();
        while(true){
            list.add(new Test());
        }
    }

    /**
     * VM Args: -Xss128k
     *
     * stackOverflowError   jvm栈溢出
     */
    public void stackOver(){
        while(true){
            stackOver();
        }
    }


    /**
     * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
     *
     * OutOfMemoryError (PermGen space)  运行时常量池（方法区）溢出
     */
    public void outOfMemory2(){
        List<String> list = new ArrayList<String>();
        int i=0;
        while(true){
            /**
             * String.intern()是一个Native方法，作用是:如果字符串常量池中已经包含一个等价于此String
             * 对象的字符串，则返回常量池中这个字符串的String对象，否则，将此String对象包含的字符串添加到常量池中，
             * 并返回此String对象的引用
             */
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * VM Args: -Xmx20M -XX:MaxDirectMemorySize=20M
     *
     * 本机直接内存可以通过 -XX:MaxDirectMemorySize指定内存大小，若不指定，则默认与java堆得最大值一样。
     *
     * OutOfMemoryError  本机直接内存溢出
     */
    public void outOfMemory3() throws Exception {
        int _1MB = 1024 * 1024;
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while(true){
            unsafe.allocateMemory(_1MB);
        }
    }

    public static void main(String[] args) throws Exception {
        Test test = new Test();

//        test.stackOver();
        test.outOfMemory();
//        test.outOfMemory2();
//        test.outOfMemory3();

        System.out.println("freeMemory:" + Runtime.getRuntime().freeMemory());
        System.out.println("maxMemory:" + Runtime.getRuntime().maxMemory());
        System.out.println("totalMemory:" + Runtime.getRuntime().totalMemory());

    }
}
