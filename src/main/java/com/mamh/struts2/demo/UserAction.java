package com.mamh.struts2.demo;

public class UserAction {
    private String userId;
    private String userName;
    private String password;
    private String desc;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String update() {
        System.out.println("update...");
        return "update-success";
    }


    public String save() {
        System.out.println("save...");




        return "input";
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


    @Override
    public String toString() {
        return "UserAction{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
