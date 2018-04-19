<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
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


<form action="product-save.action" method="post">

    productName : <input type="text" name="name">
    <br/>

    productDesc: <input type="text" name="desc">
    <br/>

    productPrice: <input type="text" name="price">
    <br/>

    <input type="submit" value="Submit">
</form>
  -->

<s:debug/>

<br/><br/>

<!-- 支持回显，使用的是栈顶元素对应属性 -->
<s:form action="user-save">
    <s:textfield name="userId" label="ID   "/>

    <s:textfield name="userName" label="username   "/>
    <s:textfield name="desc" label="desc....    "/>
    <s:password name="password" label="password...   "/>
    <s:submit/>

</s:form>


<br/><br/>

<s:radio name="xxx"  list="#{1:4,2:3}" label="xxx"/>

</body>
</html>
