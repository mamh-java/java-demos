package com.mamh.struts2.demo;

public class UserAction {


    public String update() {
        System.out.println("update...");
        return "update-success";
    }


    public String save() {
        System.out.println("save...");
        return "save-success";
    }

    public String delete() {
        System.out.println("delete....");
        return "delete-success";
    }

    public String delete1() {
        System.out.println("delete1....");
        return "delete1-success";
    }

    public String query() {
        System.out.println("query....");
        return "query-success";
    }


}
