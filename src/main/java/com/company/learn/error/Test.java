package com.company.learn.error;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/12/26.
 */
public class Test {

    /**
     * OutOfMemoryError (java heap space)  堆内存溢出
     */
    public void outOfMemory(){
        List list = new ArrayList();
        while(true){
            list.add(new Test());
        }
    }

    /**
     * stackOverflowError
     */
    public void stackOver(){
        while(true){
            stackOver();
        }
    }


    /**
     * -server -XX:PermSize=128K -XX:MaxPermSize=128K
     *
     * OutOfMemoryError (PermGen space)  运行时常量池（方法区）溢出
     */
    public void outOfMemory2(){
        List<String> list = new ArrayList<String>();
        int i=0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }

    public static void main(String[] args){
        Test test = new Test();
//        test.stackOver();

//        test.outOfMemory();

        System.out.println("freeMemory:" + Runtime.getRuntime().freeMemory());
        System.out.println("maxMemory:" + Runtime.getRuntime().maxMemory());
        System.out.println("totalMemory:" + Runtime.getRuntime().totalMemory());


//        test.outOfMemory2();


        // str的长度超过了int类型的长度 导致转换出现NumberFormatException
//        String str = "213123123123123123123";
//        Integer a = Integer.valueOf(str);
//        System.out.println(a);

        int b = 10;
        String aa = Integer.toString(b);
        System.out.println(aa + "===" + aa.getClass());

    }
}
