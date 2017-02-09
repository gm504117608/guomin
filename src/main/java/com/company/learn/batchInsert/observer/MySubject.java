package com.company.learn.batchInsert.observer;

/**
 * Created by dell on 2016/12/12.
 */
public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        notifyObservers();
    }
}
