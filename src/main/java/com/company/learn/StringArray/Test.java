package com.company.learn.StringArray;

import java.util.Random;

/**
 * Created by dell on 2017/1/10.
 */
public class Test {
    public static final String str = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String[] array = {"a","b","c","d","e","f","g","h","i","j",
            "k","l","m","n","o","p","q","r","s","t","u","v",
            "w","x","y","z","0","1","2","3","4","5","6",
            "7","8","9","A","B","C","D","E","F","G","H",
            "I","J","K","L","M","N","O","P","Q","R","S","T","U","V",
            "W","X","Y","Z"};

    public static final int len = 62;

    public static void main(String[] args) {

        Long start = System.currentTimeMillis();
        for(int i=0; i<100000; i++){
            Random r = new Random();
            int result = r.nextInt(len);

            String str2 = str.substring(result, result+1);

        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);


        Long start2 = System.currentTimeMillis();
        for(int i=0; i<100000; i++){
            Random r = new Random();
            int result = r.nextInt(len);

            String str3 = array[result];

        }
        Long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);

    }
}
