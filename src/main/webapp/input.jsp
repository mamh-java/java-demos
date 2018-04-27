<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>input.jsp</title>
</head>
<body>

<s:debug/>

<s:form method="POST" action="emp-save">
    <s:textfield name="lastName" label=" Last Name"/>
    <s:textfield name="email" label=" email"/>
    <s:textfield name="birth" label="birth"/>

    <s:select list="#request.departments" listKey="id" listValue="deptName" name="department.id" label="department"/>

    <s:submit/>
</s:form>


</body>
</html>
