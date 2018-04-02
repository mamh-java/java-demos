package com.mamh.struts2.demo;

import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.*;

import java.util.Map;

public class TestAwareAction implements ApplicationAware, SessionAware, RequestAware,HttpParametersAware {


    private Map<String, Object> application;
    private Map<String, Object> request;
    private Map<String, Object> session;

    public String execute() {
        application.put("applicationKey2", "va");
        System.out.println(application.get("date"));


        SessionMap sm = (SessionMap) session;
        sm.invalidate();
        System.out.println("session invalidate..........");

        return "success";
    }

    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }


    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setParameters(HttpParameters parameters) {

    }
}
