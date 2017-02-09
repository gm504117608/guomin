package com.company.learn.batchInsert.observer;

import com.company.learn.batchInsert.IBatchInsert;
import com.company.learn.exception.SameObserverException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dell on 2016/12/12.
 */
public abstract class AbstractSubject implements Subject {

    Set<IBatchInsert> set = new HashSet<IBatchInsert>();

    @Override
    public void add(IBatchInsert bi) {
        // 判断是否已经存在相同的订阅者
        for(IBatchInsert temp : set) {
            if(bi.getClass().getName().equals(temp.getClass().getName())){
               throw new SameObserverException("已经存在相同的观察者");
            }
        }
        set.add(bi);
    }

    @Override
    public void remove(IBatchInsert bi) {
        set.remove(bi);
    }

    @Override
    public void notifyObservers() {
        for(IBatchInsert bi : set) {
            bi.batchInsert();
        }
    }

    @Override
    public void operation() {

    }
}
