package com.atguigu.mvcapp.filter;

import javax.servlet.*;
import java.io.IOException;

public class UserNameFilter implements Filter {
    private FilterConfig filterConfig;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("UserNameFilter: doFilter");
        servletRequest.setCharacterEncoding("UTF-8");
        String initUserName = filterConfig.getInitParameter("username");
        String username = servletRequest.getParameter("username");
        if (!initUserName.equalsIgnoreCase(username)) {
            servletRequest.setAttribute("message", "用户名不正确");
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
