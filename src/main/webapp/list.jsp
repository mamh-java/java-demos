<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list.jsp</title>
</head>
<body>

<s:debug/>
<s:if test="#request.employees==null || #request.employees.size()== 0">
    没有任何员工信息
</s:if>


<s:else>
    <table border="1" cellspacing="0" cellpadding="10">
        <tr>
            <td>ID</td>
            <td>last name</td>
            <td>email</td>
            <td>brith</td>
            <td>create time</td>
            <td>department</td>
            <td></td>
        </tr>

        <s:iterator value="#request.employees">
            <tr>
                <td><s:property value="id"/></td>
                <td><s:property value="lastName"/></td>
                <td><s:property value="email"/></td>
                <td>
                    <s:date name="birth" format="yyyy-mm-dd"/>
                </td>

                <td>
                    <s:date name="createTime" format="yyyy-mm-dd  hh:mm:ss"/>
                </td>
                <td><s:property value="department.deptName"/></td>
                <td><a href="emp-delete?id=<s:property value="id"/>">delete</a></td>
            </tr>
        </s:iterator>

    </table>
</s:else>


</body>
</html>
