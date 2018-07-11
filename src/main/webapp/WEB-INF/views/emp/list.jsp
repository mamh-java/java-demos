<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>input</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var label = $(this).next(":hidden").val();
                var flag = confirm("确定删除 " + label + "的信息吗？");
                if (flag) {
                    var url = $(this).attr("href");
                    $("#_form").attr("action", url);
                    $("#_method").val("DELETE");
                    $("#_form").submit();


                }

                return false;

            })

        });
    </script>
</head>
<body>

<form action="" method="post" id="_form">
    <input type="hidden" id="_method" name="_method">
</form>

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
                    <a href="${pageContext.request.contextPath}/emp/${emp.id}"> Edit </a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/emp/${emp.id}" class="delete"> Delete </a>
                    <input type="hidden" value="${emp.id}"/>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="8">
                共&nbsp; ${page.totalElements} &nbsp; 条记录&nbsp;
                共&nbsp; ${page.totalPages} &nbsp; 页&nbsp;
                当前第&nbsp; ${page.number + 1}&nbsp; 页&nbsp;
                &nbsp; <a href="?pageNo=${page.number}">上一页</a>&nbsp;
                &nbsp; <a href="?pageNo=${page.number+2}">下一页</a>&nbsp;
            </td>

        </tr>

    </table>


</c:if>


</body>
</html>
