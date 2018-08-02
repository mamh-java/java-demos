<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-11
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="/checkCodeServlet" method="post">
        name: <input type="text" name="name" />
        checkCode: <input type="text" name="CHECK_CODE_KEY_PARAM_NAME" /> <img src="/validateColorServlet">

        <input type="submit" value="submit">

    </form>


</body>
</html>
