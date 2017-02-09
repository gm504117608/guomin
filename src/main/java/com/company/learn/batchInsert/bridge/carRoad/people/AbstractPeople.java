package com.company.learn.batchInsert.bridge.carRoad.people;

import com.company.learn.batchInsert.bridge.carRoad.road.AbstractRoad;

/**
 * Created by dell on 2016/12/21.
 */
public abstract class AbstractPeople {

    private AbstractRoad abstractRoad;
    private String people;

    public AbstractPeople(String people){
        this.people = people;
    }

    public void run(){
        System.out.print(people + "开");
        abstractRoad.run();
    }

    public AbstractRoad getAbstractRoad() {
        return abstractRoad;
    }

    public void setAbstractRoad(AbstractRoad abstractRoad) {
        this.abstractRoad = abstractRoad;
    }

}
