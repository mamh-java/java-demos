<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-14
  Time: 上午11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el.jsp</title>
</head>
<body>



<form action="el.jsp" method="post">
    <table>
        <tr>
            <td>Customer Name</td>
            <td><input type="text" name="name" value="${param.name}"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="登陆"/></td>
        </tr>

    </table>
</form>

<br/>

name: ${param.name}

</body>
</html>
