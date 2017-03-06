package com.company.learn.batchInsert.bridge.carRoad;

import com.company.learn.batchInsert.bridge.carRoad.car.AbstractCar;
import com.company.learn.batchInsert.bridge.carRoad.car.Bus;
import com.company.learn.batchInsert.bridge.carRoad.car.Car;
import com.company.learn.batchInsert.bridge.carRoad.people.AbstractPeople;
import com.company.learn.batchInsert.bridge.carRoad.people.Boy;
import com.company.learn.batchInsert.bridge.carRoad.road.AbstractRoad;
import com.company.learn.batchInsert.bridge.carRoad.road.SpeedWay;

/**
 * 桥接模式设计
 * 公路上面的车
 * 扩张路只需要继承AbstractRoad
 * 扩张车只需要继承AbstractCar
 * 将路和车之间的关系进行了分离
 *
 * 桥接模式主要是分离了对象之间的关联，解耦
 *
 * 讲解地址
 * http://blog.csdn.net/thinkinwm/article/details/8683616
 */
public class Test {

    public static void main(String[] args){
        AbstractPeople people = new Boy();
        AbstractRoad road = new SpeedWay();

        AbstractCar car = new Car();
        road.setAbstractCar(car);
        road.run();

        AbstractCar bus = new Bus();
        road.setAbstractCar(bus);
        road.run();
//
//        AbstractRoad road1 = new Street();
//
//        road1.setAbstractCar(car);
//        road1.run();
//
//        road1.setAbstractCar(bus);
//        road1.run();

        people.setAbstractRoad(road);
        people.run();

    }
}
