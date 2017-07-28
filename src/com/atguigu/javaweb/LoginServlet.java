package com.atguigu.javaweb;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class LoginServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化的
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //每次请求都会调用这个方法
        System.out.println("login servlet service....");

        //封装了请求的信息  ServletRequest servletRequest
        //封装了响应信息   ServletResponse servletResponse 这２个类的接口实现都是服务器给实现的，就是tomcat给实现的


        //封装了请求的信息  ServletRequest servletRequest
        //1.获取请求参数
        //servletRequest.getParameter(String s) 根据请求参数名字获取参数的值
        //servletRequest.getParameterMap()
        //servletRequest.getParameterNames() 
        //servletRequest.getParameterValues(String s)获取多个参数用的，返回一个数组

        String user = servletRequest.getParameter("user");
        System.out.println("user = " + user);
        String passwd = servletRequest.getParameter("password");
        System.out.println("passswd = " + passwd);

        //获取多个参数时候用的
        String[] arr = servletRequest.getParameterValues("interesting");
        System.out.println("array: = " + Arrays.asList(arr));

        Map<String, String[]> map = servletRequest.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            System.out.println("map: = " + entry.getKey() + " == " + Arrays.asList(entry.getValue()));
        }

        Enumeration<String> names = servletRequest.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] value = servletRequest.getParameterValues(name);
            System.out.println("enum : = " + name + " === " + Arrays.asList(value));
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
