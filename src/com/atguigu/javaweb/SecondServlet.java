package com.atguigu.javaweb;

import javax.servlet.*;
import java.io.IOException;

public class SecondServlet implements Servlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.print("second servlet\n");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.print("second servlet service....\n");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
