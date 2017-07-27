package com.atguigu.javaweb;

import javax.servlet.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

public class HelloServlet implements Servlet {

    public HelloServlet() {
        System.out.print("hello servlet constructor\n");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.print("init\n");

        //或者servlet配置名称，很少使用, <servlet-name>HelloServlet</servlet-name> 获取的就是这里的自己定义的名
        System.out.print("init servlet name = " + servletConfig.getServletName() + "\n");


        //获取初始化的参数
        Enumeration<String> names = servletConfig.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.print("init parameter Name = " + name + "\n");
            System.out.print("init parameter Value= " + servletConfig.getInitParameter(name) + "\n");
        }

        //下面这个是非常关键的一些  servletConfig.getServletContext(), 上下文
        //代表当前web应用的对象

        //1.获取ｗｅｂ应用的初始化参数,
        // 这个和上面的servlet初始化参数的很类似,上面是获取的某个servlet的初始化参数,servlet只有这个servlet可以获取
        // 这里获取的初始化是一个全局的
        ServletContext context = servletConfig.getServletContext();
        names = context.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.print("init context parameter Name = " + name + "\n");
            System.out.print("init context parameter Value= " + context.getInitParameter(name) + "\n");
        }


        //2.获取web应用的某一个文件的路径,获取的发布到服务器tomcat 下面的一个绝对路径
        // realPath = /var/lib/tomcat8/webapps/hello/index.jsp
        // 不是部署前的文件的路径
        String realPath = context.getRealPath("/index.jsp");
        System.out.println("init context realPath = " + realPath + "\n");

        //3.获取当前web 应用的名称
        String contextPath = context.getContextPath();
        System.out.println("init context contextPath = " + contextPath + "\n");

        //4.获取当前web 应用的某一个文件输入流
        // context.getResourceAsStream(String path); path 的 / 为当前web 应用的根目录
        //使用classLoader 来获取
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream("");
        System.out.println("1. = " + is);

        //这个是部署后的路径,这里的index.jsp就是相对于根路径下面的
        InputStream is2 = context.getResourceAsStream("index.jsp");
        System.out.println("2. = " + is2);


    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.print("get servlet config\n");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.print("service\n");
    }

    @Override
    public String getServletInfo() {
        System.out.print("get servlet info\n");
        return null;
    }

    @Override
    public void destroy() {
        System.out.print("destroy\n");
    }
}
