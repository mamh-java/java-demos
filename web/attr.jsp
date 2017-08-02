<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>attr.....</title>
</head>
<body>
<%
    //只有这４个可以设置属性，这４个称之为域对象，设置属性
    //属性作用范围仅仅限于当前页面
    pageContext.setAttribute("p", "pageContextValue");

    //仅限于同一个请求
    request.setAttribute("r", "requestValue");

    //作用范围限于一次会话,浏览器打开直道关闭，在此期间会话不失效的前提下。
    session.setAttribute("s", "sessionValue");

    //限于当前web应用，全局的范围，范围最大
    application.setAttribute("a", "applicationValue");
%>

<br><br>
pageContext: <%= pageContext.getAttribute("p") %>
<br><br>
requestContext: <%= request.getAttribute("r") %>
<br><br>
sessionContext: <%= session.getAttribute("s") %>
<br><br>
applicationContext: <%= application.getAttribute("a") %>
<br><br>


<a href="attr1.jsp">to attr1.sjp</a>

<a href="attrServlet">to attrServlet</a>

<%@include file="attr1.jsp" %>

<jsp:include page="attr1.jsp"/>

<jsp:forward page="attr1.jsp">
    <jsp:param name="username" value="xxxx"/>

</jsp:forward>
</body>
</html>
