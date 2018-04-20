<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
</head>
<body>

<s:debug/>

<table border="0.1">
    <thead>
    <tr>
        <td>ID</td>
        <td>First name</td>
        <td>Last Name</td>
        <td>Email</td>
    </tr>
    </thead>

    <tbody>
    <s:iterator value="#request.emps">
        <tr>
            <td>${employeeId}</td>
            <td>${firstName}</td>
            <td>${lastName}</td>
            <td>${email}</td>
            <td><a href="/emp-edit.action?employeeId=${employeeId}">edit</a></td>
            <td><a href="/emp-delete.action?employeeId=${employeeId}">delete</a></td>

        </tr>
    </s:iterator>

    </tbody>

</table>

</body>
</html>
