<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<body>

<form action="${pageContext.request.contextPath}/springmvc/testModelAttribute" method="post">
    <input type="hidden" name="id" value="1"/>

    username: <input type="text" name="username" value="tom"/><br/>
    email: <input type="text" name="email" value="sdfdafadf"/><br/>
    age: <input type="text" name="age" value="12"/><br/> <br/>

    <input type="submit" value="Submit"/>
</form>

<br/><br/>
<fmt:bundle basename="i18n">
    <fmt:message key="i18n.username"/> <br/><br/>


    <fmt:message key="i18n.passowrd"/> <br/><br/>
</fmt:bundle>


</body>
</html>
