<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-10
  Time: 上午10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>session hello</title>
</head>
<body>

SessionID: <%=session.getId()%>
<br><br>

ISNew: <%=session.isNew()%>
<br><br>

MaxInactiveInteval: <%=session.getMaxInactiveInterval()%>
<br><br>

CreateTime: <%=session.getCreationTime()%>
<br><br>

LastAccessTime: <%=session.getLastAccessedTime()%>
<br><br>

<%

    session.setAttribute("name", request.getParameter("name"));

%>


<a href="login.jsp">重新登陆</a>

<br><br>

<a href="logout.jsp">注销</a>

</body>
</html>
