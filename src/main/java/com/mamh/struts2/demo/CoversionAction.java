package com.mamh.struts2.demo;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

public class CoversionAction extends ActionSupport {
    private int age;
    private Date birth;

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String execute() {

        System.out.println("age = " + age);
        System.out.println(birth);

        return "success";
    }


}
