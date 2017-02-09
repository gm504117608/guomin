package com.company.learn.batchInsert.bridge.carRoad.road;

import com.company.learn.batchInsert.bridge.carRoad.car.AbstractCar;

/**
 * Created by dell on 2016/12/21.
 */
public abstract class AbstractRoad {

    private String load;

    private AbstractCar abstractCar;

    public AbstractRoad(String load){
        this.load = load;
    }

    public void run(){
        abstractCar.run();
        System.out.println("跑在" + load);
    }

    public AbstractCar getAbstractCar() {
        return abstractCar;
    }

    public void setAbstractCar(AbstractCar abstractCar) {
        this.abstractCar = abstractCar;
    }

}
