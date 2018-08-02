<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-9
  Time: 下午2:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>books.jsp</title>

</head>
<body>

<h1>books page</h1>

<a href="book.jsp?book=JavaWeb">Java Web</a> <br/><br/>
<a href="book.jsp?book=Java">Java</a> <br/><br/>
<a href="book.jsp?book=Oracle">Oracle</a> <br/><br/>
<a href="book.jsp?book=Ajax">Ajax</a> <br/><br/>
<a href="book.jsp?book=JavaScript">JavaScript</a> <br/><br/>
<a href="book.jsp?book=Android">android</a> <br/><br/>
<a href="book.jsp?book=Jbpm">Jbpm</a> <br/><br/>
<a href="book.jsp?book=Struts">Struts</a> <br/><br/>
<a href="book.jsp?book=Spring">Spring</a> <br/><br/>
<a href="book.jsp?book=Hibernate">Hibernate</a> <br/><br/>

<br/>
<br/>

<%

    Cookie[] cookies = request.getCookies();

    if (cookies != null && cookies.length > 0) {
        for (Cookie c : cookies) {
            String cName = c.getName();
            if (cName.startsWith("AUTGUIGU_BOOK_")) {
                out.print("<br/>" + c.getValue());
            }
        }
    }
%>
</body>
</html>
