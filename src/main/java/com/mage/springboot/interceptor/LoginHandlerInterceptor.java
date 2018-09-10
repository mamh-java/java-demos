package com.mage.springboot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginUser = (String) request.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            return true;//不是空说明已登录
        }
        request.setAttribute("msg", "没有权限，请先登录");
        request.getRequestDispatcher("/index.html").forward(request, response);

        return false;
    }


}
