<%@ page import="com.atguigu.mvcapp.domain.Customer" %><%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-10
  Time: 下午3:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>confirm</title>
</head>
<body>

<h1>订单确认</h1>

<%
    Customer customer = (Customer) session.getAttribute("customer");
    String[] books = (String[]) session.getAttribute("books");
%>
<table>
    <tr>
        <td>顾客姓名：</td>
        <td><%=customer.getName()%>
        </td>
    </tr>

    <tr>
        <td>顾客地址：</td>
        <td><%=customer.getAddress()%>
        </td>
    </tr>

    <tr>
        <td>顾客卡号：</td>
        <td><%=customer.getCardId()%>
        </td>
    </tr>

    <tr>
        <td>卡类型：</td>
        <td><%=customer.getCardType()%>
        </td>
    </tr>

    <tr>
        <td>Books:</td>

        <td>
            <%
                for (String book : books) {
                    out.print(book);
                    out.print("<br/>");
                }
            %>

        </td>

    </tr>


</table>

</body>
</html>
