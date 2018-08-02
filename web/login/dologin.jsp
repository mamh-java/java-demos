<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-15
  Time: 上午10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>do login</title>
</head>
<body>

<%
    String sessionKey = request.getServletContext().getInitParameter("userSessionKey");
    String username = request.getParameter("username");
    if (username != null && !username.equalsIgnoreCase("")) {
        session.setAttribute("USER_SESSION_KEY", username);
        response.sendRedirect("list.jsp");
    } else {
        response.sendRedirect("login.jsp");
    }
%>


</body>
</html>
