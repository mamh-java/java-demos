package com.atguigu.mvcapp.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 检查用户是否登陆的一个过滤器
 */
public class UserIsLoginFilter extends HttpFilter {
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
        String requestUri = request.getRequestURI();////             /hello/login/login.jsp
        String requestUrl = request.getRequestURL().toString();//    http://10.0.63.43:8080/hello/login/login.jsp
        String servletPath = request.getServletPath();//             /login/login.jsp

        System.out.println(requestUri);
        System.out.println(requestUrl);
        System.out.println(servletPath);

        //2. 检查1中获取的servletPath是否在白名单中，在白名单中的就放行，不做过滤处理
        System.out.println(urlList);
        if (urlList.contains(servletPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        //3. 这里是需要检查的url
        //3.1 从serssion中获取对应的username，这个username存在就说明登陆过了，放行，允许访问。若不存在重定向到login.jsp
        String username = (String) request.getSession().getAttribute(sessionKey);
        if (username == null) {
            System.out.println("username: " + username);
            response.sendRedirect(request.getContextPath() + redirectUrl);
            return;
        } else {

        }


        filterChain.doFilter(request, response);
    }
}
