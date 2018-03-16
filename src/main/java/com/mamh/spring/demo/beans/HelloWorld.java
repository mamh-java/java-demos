package com.mamh.spring.demo.beans;


public class HelloWorld {
    private String name;

    public HelloWorld() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName: " + name);
        this.name = name;
    }

    public void hello() {
        System.out.println("hello: = " + getName());
    }


}
