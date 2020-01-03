package com.mamh.design.demo;

public class Singleton {
    private final static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    private Singleton() {
    }
}
