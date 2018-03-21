package com.mamh.spring.demo.factory;

import com.mamh.spring.demo.beans.Car;

import java.util.HashMap;
import java.util.Map;

public class StaticCarFactory {
    /**
     * 静态工厂方法，直接调用某个类的静态方法获取bean
     */
    private static Map<String, Car> carMap = new HashMap<String, Car>();

    static {
        carMap.put("audi", new Car("audi", "sfkjs", 123));
        carMap.put("ford", new Car("ford", "slfjsks", 321));
    }

    public static Car getCar(String name) {

        return carMap.get(name);
    }
}
