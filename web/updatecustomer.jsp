<%@ page import="com.atguigu.mvcapp.domain.Customer" %><%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-6
  Time: 下午3:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<form action="update.do" method="post">
    <c:set var="id" value="${customer != null ? customer.id: param.id}"/>
    <c:set var="name" value="${customer != null ? customer.name: param.name}"/>
    <c:set var="oldname" value="${customer != null ? customer.name: param.oldname}"/>
    <c:set var="address" value="${customer != null ? customer.address: param.address}"/>
    <c:set var="phone" value="${customer != null ? customer.phone: param.phone}"/>


    <p>
        ${requestScope.message}
    </p>


    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="oldName" value="${oldname}"/>

    <table>
        <tr>
            <td>Customer Name</td>
            <td><input type="text" name="name" value="${name}"/></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="address" value="${address}"/></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="text" name="phone" value="${phone}"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="update"/></td>
        </tr>

    </table>
</form>
</body>
</html>
