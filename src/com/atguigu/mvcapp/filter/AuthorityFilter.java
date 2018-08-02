package com.atguigu.mvcapp.filter;

import com.atguigu.mvcapp.domain.Authority;
import com.atguigu.mvcapp.domain.Permission;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthorityFilter extends HttpFilter {
    private String sessionKey;
    private String redirectUrl;
    private String unCheckUrls;
    private List<String> urlList;

    @Override
    public void init() {
        super.init();
        ServletContext servletContext = getFilterConfig().getServletContext();

        sessionKey = servletContext.getInitParameter("userSessionKey");
        redirectUrl = servletContext.getInitParameter("redirectPage");
        unCheckUrls = servletContext.getInitParameter("unCheckedUrls");

        String[] temp = unCheckUrls.split(",");
        urlList = Arrays.asList(temp);

    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        //1 获取请求的url路径
        String servletPath = request.getServletPath();//             /login/login.jsp

        System.out.println(servletPath);

        //1.获取servletPath

        String serveltPath = request.getServletPath();
        System.out.println(urlList);
        //2.前提用户已经登陆（使用用户是否登陆过滤器）获取用户信息，用户信息是在session中保存的
        if (urlList.contains(serveltPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        //3.获取用户具有的权限信息，权限是一个list
        Permission user = (Permission) request.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("user = " + user);
            response.sendRedirect("/permission/login.jsp");
            return;
        }

        List<Authority> authorities = user.getAuthorities();
        //4.检验用户是否有访问这serveltPath的权限，遍历list依次判断.是否还有其他更好的方式？？？
        Authority authority = new Authority(null, serveltPath);
        if (authorities.contains(authority)) {
            filterChain.doFilter(request, response);
            return;
        }
        //5.若有权限就访问，就响应。若没有权限就重定向到403.jsp这个页面
        response.sendRedirect("/permission/403.jsp");

        return;
    }
}
