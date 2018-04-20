package com.mamh.struts2.demo;

import org.apache.struts2.interceptor.RequestAware;

import java.util.List;
import java.util.Map;

public class Employee implements RequestAware {
    private Map<String, Object> request;

    private Dao dao = new Dao();


    private String name;
    private String password;
    private String dept;
    private List<String> role;
    private boolean gender;
    private String desc;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String input() {
        request.put("depts", dao.getDepartments());
        request.put("roles", dao.getRoles());

        System.out.println("input......................");

        return "input";
    }


    public String save() {
        System.out.println("save.......................");


        return "save";
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;

    }


}
