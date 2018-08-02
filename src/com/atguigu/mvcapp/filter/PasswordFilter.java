package com.atguigu.mvcapp.filter;

import javax.servlet.*;
import java.io.IOException;

public class PasswordFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    private FilterConfig filterConfig;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String initPassword = filterConfig.getServletContext().getInitParameter("password");


        String password = servletRequest.getParameter("password");
        if (!initPassword.equalsIgnoreCase(password)) {
            servletRequest.setAttribute("message", "密码不正确");
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
