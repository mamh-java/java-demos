<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-10
  Time: 上午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>session login</title>
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
    String name = (String) session.getAttribute("name");
    if (name == null) {
        name = "";
    }
%>

<form action="hello.jsp" method="post">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="<%=name%>"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="登陆"/></td>
        </tr>

    </table>
</form>

</body>
</html>
