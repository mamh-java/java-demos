package com.mamh.springmvc.demo.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstInterceptor implements HandlerInterceptor {


    /**
     * 该方法在目标方法之前被调用。
     * 若返回是true，则继续调用后续的拦截器和目标方法。
     * 若返回是false，则不会继续调用后续拦截器，也不会调用目标方法了
     *
     * 这个方法可以考虑做权限，日志，事务
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("FirstInterceptor preHandle");
        return true;
    }

    /**
     * 在调用目标方法之后，渲染视图之前被调用
     *
     * 这个方法可以用来对请求域中属性或者视图做出修改。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("FirstInterceptor postHandle");
    }

    /**
     * 渲染视图之后被调用
     *
     * 这个方法可以用来释放资源用的
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("FirstInterceptor afterCompletion");
    }
}
