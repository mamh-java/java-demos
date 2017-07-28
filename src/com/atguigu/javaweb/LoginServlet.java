package com.atguigu.javaweb;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class LoginServlet implements Servlet {
    private String mUser;
    private String mPassword;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化的

        //mUser = servletConfig.getInitParameter("user");
        //mPassword = servletConfig.getInitParameter("password");
        mUser = servletConfig.getServletContext().getInitParameter("user");
        mPassword = servletConfig.getServletContext().getInitParameter("password");

        System.out.println("mUser = " + mUser);
        System.out.println("mPassword = " + mPassword);
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

        //　子接口，真的ｈｔｔｐ请求的
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println("uri = " + httpServletRequest.getRequestURI());
        System.out.println("url = " + httpServletRequest.getRequestURL());
        System.out.println("method = " + httpServletRequest.getMethod());
        System.out.println("servlet path = " + httpServletRequest.getServletPath());
        System.out.println("query stirng = " + httpServletRequest.getQueryString()); //ｐｏｓｔ请求这个是ｎｕｌｌ


        // servletResponse 响应
        servletResponse.setContentType("text/plain"); //设置响应的内容类型
        PrintWriter writer = servletResponse.getWriter();//返回一个PrintWriter对象
        if (user.equals(mUser) && passwd.equals(mPassword)) {
            writer.println("hello world");// 这里可以打印到浏览器上面
        } else {
            writer.println("sorry: " + user);// 这里可以打印到浏览器上面
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
