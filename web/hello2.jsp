<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //介绍模板元素

    //1.jsp 表达式可以将java 变量或java 表达式直接在页面上面输出
    Date date = new Date();
    out.print(date);      //可以这样写

    String realPath = config.getServletContext().getRealPath("/index.jsp");
    System.out.println("init context realPath = " + realPath + "\n");
%>

<br><br>

<%= date %>


<%
    String ageStr = request.getParameter("age");
    int age = 0;
    try {
        age = Integer.parseInt(ageStr);
    } catch (Exception e) {

    }
    if (age >= 18) {
%>
成人
<%
} else {
%>
未成年
<%
    }


%>


<%!
    void test() {
        System.out.println("this is test method");
    }

%>


</body>
</html>
