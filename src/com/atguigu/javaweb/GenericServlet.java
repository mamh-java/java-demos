package com.atguigu.javaweb;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

public abstract class GenericServlet implements Servlet, ServletConfig {
    private ServletConfig mServletConfig;
    private ServletContext mServletContext;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        mServletConfig = servletConfig;
        mServletContext = servletConfig.getServletContext();

    }

    @Override
    public ServletConfig getServletConfig() {
        return mServletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public String getServletName() {
        return mServletConfig.getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return mServletContext;
    }

    @Override
    public String getInitParameter(String s) {
        return mServletConfig.getInitParameter(s);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return mServletConfig.getInitParameterNames() ;
    }
}
