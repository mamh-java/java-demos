<%@ page import="com.atguigu.mvcapp.domain.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>jstl.jsp</title>
</head>
<body>

<!-- 表达式操作 -->

<br/><br/>
<%
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer(1, "aa", "aa", "bbb"));
    customers.add(new Customer(2, "bb", "aa", "bbb"));
    customers.add(new Customer(3, "cc", "aa", "bbb"));
    customers.add(new Customer(4, "dd", "aa", "bbb"));
    customers.add(new Customer(5, "ee", "aa", "bbb"));
    customers.add(new Customer(6, "ff", "aa", "bbb"));
    pageContext.setAttribute("customers", customers);

    Map<String, Customer> customerMap = new HashMap<>();
    customerMap.put("index0", customers.get(0));
    customerMap.put("index1", customers.get(1));
    customerMap.put("index2", customers.get(2));
    pageContext.setAttribute("customermap", customerMap);

    String[] names = new String[3];
    names[0] = "aaaaaaaaaaaa";
    names[1] = "AAAAAAAAAAAA";
    names[2] = "bbbbbbbbbbbb";
    pageContext.setAttribute("names", names);
%>

<c:forEach items="${pageScope.customers}" var="c" varStatus="status">
    ${c.name}, ${status.index}<br/>
</c:forEach>

<br/>
<br/>
<c:forEach items="${pageScope.customermap}" var="c" varStatus="status">
    ${c.key} = ${c.value} <br/>
</c:forEach>

<br/>
<br/>

<c:forEach items="${pageScope.names}" var="c" varStatus="status">
    ${c} ==<br/>
</c:forEach>

<br/>
<br/>

<c:forTokens items="a b  c d e" delims=" " var="c">
    ${c}<br>
</c:forTokens>

<br>
<br>
<c:url var="testurl" value="/login.jsp" scope="page">
    <c:param name="url" value="aguigu"/>
</c:url>

${testurl}

</body>
</html>
</body>
</html>
