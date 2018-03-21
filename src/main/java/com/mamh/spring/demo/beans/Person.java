package com.mamh.spring.demo.beans;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Person {
    private String name;
    private int age;
    private Car car;
    private List<Car> cars;
    private Map<String, Car> carMap;

    private Properties properties;

    private String city;
    private String info;//根据car的price确定 info，金领，白领等。

    public Person() {
    }

    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Map<String, Car> getCarMap() {
        return carMap;
    }

    public void setCarMap(Map<String, Car> carMap) {
        this.carMap = carMap;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                ", cars=" + cars +
                ", carMap=" + carMap +
                ", properties=" + properties +
                ", city='" + city + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
