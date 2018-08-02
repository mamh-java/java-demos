<%@ page import="com.atguigu.mvcapp.domain.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
</head>
<body>

<form action="query.do" method="post">
    <table>
        <tr>
            <td>Customer Name</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="address"/></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="text" name="phone"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="query"/></td>
            <td><a href="newcustomer.jsp"> Add a new Cumtormer</a></td>
        </tr>

    </table>
</form>

<br>
<br>
<br>

<c:if test="${ !empty requestScope.customers}">

<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>CustomerName</th>
        <th>Address</th>
        <th>Phone</th>
        <th>UPDATE \ DELETE</th>
    </tr>
    <c:forEach items="${requestScope.customers}" var="customer">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.address}</td>
            <td>${customer.phone}</td>
            <c:url var="deleteurl" value="/delete.do">
                <c:param name="id" value="${customer.id}"/>
            </c:url>
            <c:url var="editurl" value="/edit.do">
                <c:param name="id" value="${customer.id}"/>
            </c:url>

            <td>
                <a href="${deleteurl}">DELETE</a>
                <a href="${editurl}">UPDATE</a>

            </td>
        </tr>
    </c:forEach>
    </c:if>

</table>

</body>
</html>
