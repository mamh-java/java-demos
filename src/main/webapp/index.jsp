<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
<body>

<%
    session.setAttribute("date", new Date());
%>
<!--
 利用s:propperty标签和OGNL表达式读取值栈中的属性值

 对象栈

 map栈：request，session，application

 -->
<form action="product-save.action" method="post">

    productName : <input type="text" name="name">
    <br/>

    productDesc: <input type="text" name="desc">
    <br/>

    productPrice: <input type="text" name="price">
    <br/>

    <input type="submit" value="Submit">
</form>


</body>
</html>
