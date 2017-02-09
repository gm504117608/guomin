package com.company.learn.batchInsert.iterator;

import com.company.learn.batchInsert.iterator.impl.MyCollection;

/**
 * 迭代器模式测试
 */
public class Test {
    public static void main(String[] args){
        Collection c = new MyCollection(4);
        c.add(1);
        c.add(2);
        c.add(3);
        c.add(4);
        Iterator it = c.getIterator();
        while(it.hasNext()){
            System.out.println("值：" + it.next());
        }
    }
}
