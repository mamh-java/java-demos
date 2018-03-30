package com.mamh.struts2.demo;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterDispacter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        //1.获取servlet path，
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String servletPath = httpServletRequest.getServletPath();
        System.out.println(servletPath);

        String path = null;
        //2.根据servletpath判断是product-input还是product-save
        if ("/product-input.action".equalsIgnoreCase(servletPath)) {
            path = "/WEB-INF/pages/input.jsp";
        } else if ("/product-save.action".equalsIgnoreCase(servletPath)) {
            path = "/WEB-INF/pages/details.jsp";
            //2.1获取请求参数
            String productName = httpServletRequest.getParameter("productName");
            String productDesc = httpServletRequest.getParameter("productDesc");
            String productPrice = httpServletRequest.getParameter("productPrice");

            //2.2把请求参数封装为一个product对象
            Product product = new Product(productName, productDesc, productPrice);

            //2.3执行保存操作,这里不真正保存数据库，这里只是模拟一下保存操作
            System.out.println("save product: " + product);
            product.setId(1);


            //2.4 把product对象保存到request中
            servletRequest.setAttribute("product", product);
        } else {
            System.out.println("xxx");
        }
        if (path != null) {
            servletRequest.getRequestDispatcher(path).forward(servletRequest, servletResponse);
            return;
        }


        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {

    }
}
