package com.mamh.spring.demo.factory;

import com.mamh.spring.demo.beans.Car;

import java.util.HashMap;
import java.util.Map;

public class InstanceCarFactory {
    /**
     * 实例工厂的方法，即先需要创建工厂实例本身，然后再调用工厂中的方法，来返回bean实例。
     */
    private Map<String, Car> carMap;

    public InstanceCarFactory() {
        carMap = new HashMap<String, Car>();
        carMap.put("audi", new Car("audi", "sfkjs", 123));
        carMap.put("ford", new Car("ford", "slfjsks", 321));
    }

    public Car getCar(String name) {

        return carMap.get(name);
    }

}
