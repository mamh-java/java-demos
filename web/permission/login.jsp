<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-9
  Time: 下午12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
</head>
<body>
${message}
<br/>
<form action="/loginPermissionServlet?method=login" method="post">
    <table>
        <tr>
            <td>Customer username</td>
            <td><input type="text" name="username" value="${param.username}"/></td>
        </tr>
        <tr>
            <td>Customer password</td>
            <td><input type="password" name="password" value="${param.password}"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="登陆"/></td>
        </tr>

    </table>
</form>
</body>
</html>
