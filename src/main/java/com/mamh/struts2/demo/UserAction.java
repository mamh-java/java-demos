package com.mamh.struts2.demo;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class UserAction implements SessionAware, ApplicationAware {
    private Map<String, Object> applicationMap;
    private Map<String, Object> sessionMap;
    private String username;


    public String logout() throws Exception {
        Integer count = (Integer) applicationMap.get("count");
        if (count != null && count > 0) {
            count--;
            applicationMap.put("count", count);
        }

        ((SessionMap) sessionMap).invalidate();

        System.out.println("user action: logout().....");
        return "success";
    }

    public String execute() {
        //把用户信息存入到session中
        //1.获取session，通过requestaware接口

        //2.获取登陆信息，通过action的setter方法 ,这个是自动设置的。username要和form表单的中的名字一致。

        //3.把用户信息存入session中。
        sessionMap.put("username", username);


        //在线人数加一
        Integer count = (Integer) applicationMap.get("count");
        if (count == null) {
            count = 0;
        }
        count++;
        applicationMap.put("count", count);

        return "success";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSession(Map<String, Object> session) {
        this.sessionMap = session;
    }

    public void setApplication(Map<String, Object> application) {
        this.applicationMap = application;
    }


}
