<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<head>
    <title>edit</title>
</head>
<body>
<s:form action="emp-update.action">
    <s:hidden name="employeeId"/>
    <s:textfield name="firstName" label="first name"/>
    <s:textfield name="lastName" label="lastName"/>
    <s:textfield name="email" label="email"/>
    <s:submit/>
</s:form>


</body>
</html>
