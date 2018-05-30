<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var href = $(this).attr("href");
                $("form").attr("action", href).submit();
                return false;

            });

        })
    </script>
</head>
<body>

<form action="" method="post">
    <input type="hidden" value="DELETE" name="_method"/>
</form>

<c:if test="${empty requestScope.employees}">
    没有任何信息
</c:if>
<c:if test="${!empty requestScope.employees}">
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Department</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${requestScope.employees}" var="emp">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender == 0? 'Female': 'Male'}</td>
                <td>${emp.department.departmentName}</td>
                <td><a href="/emp/${emp.id}">Edit</a></td>
                <td><a class="delete" href="/emp/${emp.id}">Delete</a></td>
            </tr>
        </c:forEach>

    </table>
</c:if>

<br/>
<a href="/emp">Add new Employee.........</a>


</body>
</html>
