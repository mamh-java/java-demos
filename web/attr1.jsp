<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-2
  Time: 下午7:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>attr1.jsp</title>
    
</head>
<body>
<br><br>
pageContext: <%= pageContext.getAttribute("p") %>
<br><br>
requestContext: <%= request.getAttribute("r") %>
<br><br>
sessionContext: <%= session.getAttribute("s") %>
<br><br>
applicationContext: <%= application.getAttribute("a") %>
<br><br>
</body>
</html>
