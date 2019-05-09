<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>list</title>
</head>
<body>

Welcome <shiro:principal/> to list jsp page <br/>

<shiro:hasRole name="admin">
<a href="/admin.jsp">admin</a><br/>
</shiro:hasRole>

<shiro:hasRole name="user">
<a href="/user.jsp">user</a><br/>
</shiro:hasRole>

<shiro:authenticated>
<br/><a href="/shiro/logout">logout</a><br/>
</shiro:authenticated>

</body>
</html>
