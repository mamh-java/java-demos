<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mamh
  Date: 17-8-9
  Time: 下午2:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>book.js</title>
</head>
<body>

<h1>book detail page</h1>

<br/>
<br/>
<a href="books.jsp">return</a>
<br/>
<br/>

<%

    String book = request.getParameter("book");


    Cookie[] cookies = request.getCookies();
    List<Cookie> bookCookieList = new ArrayList<Cookie>();
    Cookie tempCookie = null;

    if (cookies != null && cookies.length > 0) {
        for (Cookie c : cookies) {
            String cName = c.getName();
            if (cName.startsWith("AUTGUIGU_BOOK_")) {
                bookCookieList.add(c);
                if (c.getValue().equals(book)) {
                    tempCookie = c;//用来保存和books.jsp 传过来的匹配的哪个cookie
                }

            }
        }
    }

    if (bookCookieList.size() >= 5 && tempCookie == null) {
        tempCookie = bookCookieList.get(0);
    }

    if (tempCookie != null) {
        tempCookie.setMaxAge(0);
        response.addCookie(tempCookie);
    }

    Cookie cookie = new Cookie(String.format("AUTGUIGU_BOOK_%s", book), book);
    response.addCookie(cookie);


%>


</body>
</html>
