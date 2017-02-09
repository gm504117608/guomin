package com.company.learn.batchInsert.iterator.impl;

import com.company.learn.batchInsert.iterator.Collection;
import com.company.learn.batchInsert.iterator.Iterator;

/**
 * Created by dell on 2016/12/13.
 */
public class MyCollection implements Collection {

    private Object[] array = null;

    private int size = -1;

    private int pos = -1;

    public MyCollection(){
        this(10);
    }

    public MyCollection(int len){
        size = len;
        array = new Object[len];
    }

    @Override
    public Iterator getIterator() {
        return new MyIterator(this);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        if(i<0 || i>size){
            throw new ArrayIndexOutOfBoundsException("数组下标异常" + i + "数组长度" + size);
        }
        return array[i];
    }

    @Override
    public void add(Object value) {
        if(pos > size){
            throw new ArrayIndexOutOfBoundsException("数组下标异常" + size);
        }
        pos++;
        array[pos] = value;
    }
}
