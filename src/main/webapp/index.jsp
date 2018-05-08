<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<a href="/emps">List All Employees</a>
<br><br>

<form action="/testConverter" method="post">

    <!-- 把字符串直接转换为employee对象
    Employee [id=null, lastName=, email=, gender=null, department=Department [id=101, departmentName=null], birth=Thu Dec 06 00:00:00 CST 1990, salary=null]

     -->
    <input type="text" name="employee"/>

    <input type="submit" value="submit"/>
</form>

</body>
</html>
