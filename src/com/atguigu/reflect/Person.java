package com.atguigu.reflect;

public class Person {
    public String name;
    private int age;
    protected int age1;
    int age2;
    private static int age3;

    public Person() {
    }


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    public static void show() {
        System.out.println("我是一个人！");
    }

    public void display(String nation) {
        System.out.println("我的国籍是：" + nation);
    }
}
