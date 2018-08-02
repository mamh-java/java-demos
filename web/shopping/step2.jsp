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
    <title>step 2.jsp</title>
</head>
<body>


<H1>请输入寄送信息和信用卡信息</H1>

<form action="<%=request.getContextPath()%>/processStep2" method="post">
    <table cellspacing="0" cellpadding="10" border="1">

        <tr>
            <td colspan="3">寄送信息</td>
        </tr>

        <tr>
            <td>姓名</td>
            <td colspan="2"><input type="text" name="name" value=""/></td>
        </tr>
        <tr>
            <td>寄送地址</td>
            <td colspan="2"><input type="text" name="address" value=""/></td>
        </tr>

        <tr>
            <td colspan="3">信用卡信息</td>
        </tr>
        <tr>
            <td>种类</td>
            <td><input type="radio" name="cardtype" value="visa"/></td>
            <td><input type="radio" name="cardtype" value="master"/></td>
        </tr>
        <tr>
            <td>卡号</td>
            <td colspan="2"><input type="text" name="cardid" value=""/></td>
        </tr>

        <tr>
            <td colspan="3"><input type="submit" value="继续"/></td>
        </tr>

    </table>
</form>

</body>
</html>
