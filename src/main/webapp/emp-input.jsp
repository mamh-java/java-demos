<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>empt-input</title>
</head>
<body>

<s:form action="emp-save" theme="xhtml">
    <s:textfield name="name" label="Name"/>
    <s:password name="password" label="Password"/>

    <s:radio name="gender" list="#{'1':'Male','2':'Female'}" label="Gender"/>

    <s:select list="#request.depts" listKey="deptId" listValue="deptName" name="dept" label="Department"/>

    <s:checkboxlist list="#request.roles" listKey="roleId" listValue="roleName" name="role" label="Role"/>

    <s:textarea name="desc" label="Desc"/>

    <s:submit/>

</s:form>


</body>
</html>
