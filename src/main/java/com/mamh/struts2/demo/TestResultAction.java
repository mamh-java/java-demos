package com.mamh.struts2.demo;

import com.opensymphony.xwork2.ActionSupport;

public class TestResultAction extends ActionSupport {

    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String execute() throws Exception {
        String result;
        if (number % 4 == 0) {
            result = "success";
        } else if (number % 4 == 1) {
            result = "login";
        } else if (number % 4 == 2) {
            result = "index";
        } else {
            result = "logout";
        }

        System.out.println(result);
        return result;
    }
}
