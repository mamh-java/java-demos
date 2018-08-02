<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-17
  Time: 下午4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>403.jsp</title>
</head>
<body>
<h1>没有对于的权限</h1>
<a href="">返回</a>

<!--
使用filter进行过滤

1.获取servletPath
2.前提用户已经登陆（使用用户是否登陆过滤器）获取用户信息，用户信息是在session中保存的
3.获取用户具有的权限信息，权限是一个list
4.检验用户是否有访问这serveltPath的权限，遍历list依次判断.是否还有其他更好的方式？？？
5.若有权限就访问，就响应。若没有权限就重定向到403.jsp这个页面

用户若登陆，需要把用户信息放到

-->
</body>
</html>
