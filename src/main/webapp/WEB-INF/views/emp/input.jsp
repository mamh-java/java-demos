<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#lastName").change(function () {
                var val = $(this).val();
                val = $.trim(val);
                $(this).val(val);

                var url = "${pageContext.request.contextPath}/ajaxValidateLastName";
                var args = {"lastName": val, "date": new Date()};

                $.post(url, args, function (data) {
                    if (data == "0") {
                        alert("可以使用");
                        console.log("ok");
                    } else if (data == "1") {
                        alert("不可以使用");
                        console.log("no ok");

                    }
                });

            })
        })
    </script>
</head>
<body>


<form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">

    <table border="=1" cellpadding="10" cellspacing="0">

        <tr>
            <td>
                LastName
            </td>
            <td><form:input path="lastName" id="lastName"/>
            </td>
        </tr>
        <tr>
            <td>
                Email
            </td>
            <td><form:input path="email"/>
            </td>
        </tr>

        <tr>
            <td>
                Birth
            </td>
            <td><form:input path="birth"/>
            </td>
        </tr>


        <tr>
            <td>
                Department
            </td>
            <td><form:select path="department.id" items="${departments}" itemValue="id" itemLabel="departmentName"/>
            </td>

        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Submit!">
            </td>
        </tr>
    </table>

</form:form>

</body>
</html>
