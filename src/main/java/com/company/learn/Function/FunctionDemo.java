package com.company.learn.Function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by dell on 2016/10/9.
 */
public class FunctionDemo {

    static void modifyTheValue(int value, Function<Integer, Integer> function){
        int newVal = function.apply(value);
        System.out.println(newVal);
    }

    public static void main(String[] args){
        int incr = 20, myNum = 10;
//        modifyTheValue(myNum, val -> val + incr);

//        Function<String,String> function = (x) -> {System.out.print(x+": ");return "Function";};
//        System.out.println(function.apply("hello world"));


        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);
        // 以前的循环方式
        for (String player : players) {
            System.out.println(player + "; ");
        }
        System.out.println("========================");
        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> System.out.println(player + "; "));
        players.forEach((player) -> {
            String str = "222";
            player = player + str;
            System.out.println(player + "; ");
        });
        System.out.println("========================");
        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);

    }
}
