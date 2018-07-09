<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${page == null} || page.numberOfElements == 0}">
    没有任何记录
</c:if>
<c:if test="${page != null && page.numberOfElements >0}">
    <table border="=1" cellpadding="10" cellspacing="0">
        <tr>
            <td>
                ID
            </td>
            <td>
                LastName
            </td>
            <td>
                Email
            </td>
            <td>
                Birth
            </td>
            <td>
                Create Time
            </td>
            <td>
                Department
            </td>
            <td>
                Edit
            </td>
            <td>
                Delete
            </td>
        </tr>

        <c:forEach items="${page.content}" var="emp">
            <tr>
                <td>
                        ${emp.id}
                </td>
                <td>
                        ${emp.lastName}
                </td>
                <td>
                        ${emp.email}
                </td>
                <td>
                    <fmt:formatDate value="${emp.birth}" pattern="yyyy-MM-dd"/>
                </td>
                <td>
                    <fmt:formatDate value="${emp.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                </td>
                <td>
                    ${emp.department.departmentName}
                </td>
                <td>
                    Edit
                </td>
                <td>
                    Delete
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="8">
                共&nbsp;     ${page.totalElements} &nbsp; 条记录&nbsp;
                共&nbsp;     ${page.totalPages} &nbsp; 页&nbsp;
                当前第&nbsp;  ${page.number + 1}&nbsp; 页&nbsp;
                &nbsp; <a href="?pageNo=${page.number}">上一页</a>&nbsp;
                &nbsp; <a href="?pageNo=${page.number+2}">下一页</a>&nbsp;
            </td>

        </tr>

    </table>


</c:if>


</body>
</html>
