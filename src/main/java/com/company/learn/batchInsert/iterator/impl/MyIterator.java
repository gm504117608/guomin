package com.company.learn.batchInsert.iterator.impl;

import com.company.learn.batchInsert.iterator.Collection;
import com.company.learn.batchInsert.iterator.Iterator;

/**
 * Created by dell on 2016/12/13.
 */
public class MyIterator implements Iterator {

    private Collection collection;
    private int pos = -1; // 当前位置

    public MyIterator(Collection collection){
        this.collection = collection;
    }

    @Override
    public Object previous() {
        isNull();
        if(pos < 0){
            throw new ArrayIndexOutOfBoundsException("数组下标越界" + pos);
        }
        pos--;
        return collection.get(pos);
    }

    @Override
    public Object next() {
        isNull();
        int size = collection.size()-1;
        if(pos > size){
            throw new ArrayIndexOutOfBoundsException("数组下标越界" + pos);
        }
        pos++;
        return collection.get(pos);
    }

    @Override
    public boolean hasNext() {
        isNull();
        int size = collection.size()-1;
        if(pos < size){
            return true;
        }
        return false;
    }

    @Override
    public Object first() {
        isNull();
        return collection.get(0);
    }

    @Override
    public void isNull() {
        if(null == collection){
            throw new NullPointerException("集合元素为空");
        }
    }
}
