package com.atguigu.mvcapp.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContentFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ContentFilter = ");
        String content = request.getParameter("content");
        if (content != null && content.contains(" fuck ")) {

        }

        filterChain.doFilter(request, response);
    }
}
