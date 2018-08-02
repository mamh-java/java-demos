<%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-10
  Time: 下午3:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>step 1.jsp</title>
</head>
<body>


<H1>选择要购买的图书</H1>


<form action="<%=request.getContextPath()%>/shopping/processStep1" method="post">
    <table cellspacing="0" cellpadding="10" border="1">
        <tr>
            <th>书名</th>
            <th>选择是否购买</th>
        </tr>


        <tr>
            <td>Java</td>
            <td><input type="checkbox" name="book" value="Java"/></td>
        </tr>
        <tr>
            <td>Oracle</td>
            <td><input type="checkbox" name="book" value="Oracle"/></td>
        </tr>
        <tr>
            <td>Struts</td>
            <td><input type="checkbox" name="book" value="Struts"/></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="继续"/></td>
        </tr>

    </table>
</form>

</body>
</html>
