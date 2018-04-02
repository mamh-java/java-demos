package com.mamh.struts2.demo;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServletAwareAction implements ServletContextAware, ServletRequestAware, ServletResponseAware {


    private ServletContext servletContext;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setServletContext(ServletContext context) {
        this.servletContext = context;

    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;

    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String execute() {

        System.out.println(servletContext);
        System.out.println(request);

        System.out.println(response);
        return "success";
    }
}
