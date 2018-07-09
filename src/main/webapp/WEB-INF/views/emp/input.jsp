<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form:form action="${pageContext}/emp" method="post" modelAttribute="employee">

    <table border="=1" cellpadding="10" cellspacing="0">

        <tr>
            <td>
                LastName
            </td>
            <td><form:input path="lastName"/>
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
