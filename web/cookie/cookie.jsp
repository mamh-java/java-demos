<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-9
  Time: 上午10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cookie</title>
</head>
<body>


<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
        for (Cookie c : cookies) {
            out.print(c.getName() + ": " + c.getValue());
            out.print("<br/>");
        }
    } else {
        out.print("没有一个cookie，将会创建一个");
        //创建一个cookie对象
        Cookie cookie = new Cookie("name", "atguigu");

        cookie.setMaxAge(30);//30秒
        //调用response的一个方法把cookie传个客户端
        response.addCookie(cookie);

    }

%>

</body>
</html>
