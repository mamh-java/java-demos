package com.mamh.mybatis.demo.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String name;
    private Date brithday;
    private float salary;

    public User() {

    }

    public User(String name, Date brithday, float salary) {
        this.name = name;
        this.brithday = brithday;
        this.salary = salary;
    }

    public User(int id, String name, Date brithday, float salary) {
        this.id = id;
        this.name = name;
        this.brithday = brithday;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brithday=" + brithday +
                ", salary=" + salary +
                '}';
    }
}
