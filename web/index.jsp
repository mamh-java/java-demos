<%@ page import="com.atguigu.javaweb.Person" %>
<%@ page import="com.atguigu.javaweb.HelloServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>

<%
    //9 个隐含对象
    //1.HttpServletRequest类的一个对象
    String username = request.getParameter("username");
    System.out.println(username);

    //2.HttpServletResponse类的一个对象,这个几乎不会调用
    Class clazz = response.getClass();
    System.out.println(clazz);

    //3.PageContext,页面的上下文，重要的一个对象。
    // 可以从改对象中获取页面所有信息，可以获取其他的８个隐含对象(后面学习自定义标签使用它)
    HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
    System.out.println(req == request);

    //4.session,HttpSession类的一个对象，代表浏览器和服务器的一次会话
    System.out.println("session ID = " + session.getId());


    //5.application,代表当前web应用，可以获取web应用的初始化参数
    System.out.println(application.getInitParameter("user"));
    

    //6.config,当前jsp对象的servlet的ServletConfig对象，几乎不用
    System.out.println(config.getInitParameter("hellojsp.password"));


    //7.out, JspWriter　对象　可以直接调用out.println(),可以把字符串都打印到页面上
    out.println(config.getInitParameter("hellojsp.password"));
    out.print("<br/>");
    out.println("session ID = " + session.getId());


    //8.page是Obejct的对象，等于是this,    final java.lang.Object page = this;
    //指向当前ｊｓｐ对象的一个引用,但是他是一个Object类型只能使用Object　类的方法，几乎不用
    out.print("<br/>");
    out.print(this);
    out.print("<br/>");
    out.print(page);

    //9.exception  只有在error 页面才可以使用



%>


</body>
</html>







</body>
</html>
