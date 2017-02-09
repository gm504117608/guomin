package com.company.learn.batchInsert.bridge.carRoad.car;

/**
 * Created by dell on 2016/12/21.
 */
public abstract class AbstractCar {

    private String car;

    public AbstractCar(String car){
        this.car = car;
    }

    public void run(){
        System.out.print(car);
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

}
