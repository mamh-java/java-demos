<%@ page import="java.util.Date" %>
<html>
<body>
<a href="product-execute.action"> product execute</a>
<br/>
<a href="product-input.action"> product input</a>
<br/>
<a href="testAware.action"> testAware.action</a>


<%
    if (application.getAttribute("date") == null) {
        application.setAttribute("date", new Date());
    }
    request.setAttribute("req", "reqvalue");
%>
</body>
</html>
