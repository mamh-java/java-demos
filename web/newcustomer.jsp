<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-6
  Time: 下午3:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="add.do" method="post">
    <p>
        ${requestScope.message}
    </p>


    <table>
        <tr>
            <td>Customer Name</td>
            <td><input type="text" name="name" value="${param.name}"/></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="address" value="${param.address}"/></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="text" name="phone" value="${param.phone}"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="add"/></td>
        </tr>

    </table>
</form>
</body>
</html>
