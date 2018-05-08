<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>input</title>
</head>
<body>

<!--
可以更快速的开发出表单页面，方便表单值回显

java.lang.IllegalStateException: Neither BindingResult nor plain target object for bean name 'command' available as request attribute

可以通过 modelAttribute 属性指定绑定的模型属性，
若没有指定该属性，则默认从 request 域对象中读取
command 的表单 bean，如果该属性值也不存在，则会
发生错误。

-->

<form:form action="/emp" method="POST" modelAttribute="employee">
    <c:if test="${employee.id == null}">
        LastName: <form:input path="lastName"/> <br/>
    </c:if>

    <c:if test="${employee.id != null}">
        <form:hidden path="id"/>
        <input type="hidden" name="_method" value="PUT"/>
    </c:if>
    Email: <form:input path="email"/> <br/>

    <%
        Map<String, String> genders = new HashMap();
        genders.put("1", "Male");
        genders.put("0", "Female");
        request.setAttribute("genders", genders);
    %>
    Gender: <form:radiobuttons path="gender" items="${genders}"/> <br/>

    Birth: <form:input path="birth"/><br/>

    Department: <form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/>
    <br/>


    <input type="submit" value="submit"/>
</form:form>


</body>
</html>
