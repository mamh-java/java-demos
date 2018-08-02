<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>authority-manager.jsp</title>
</head>
<body>

<H1>authority-manager.jsp</H1>


<div style="text-align: center;background-color: antiquewhite">
    <br/><br/>
    <form action="/authorityServlet?method=getAuthorities" method="post">
        <table style="margin: auto">
            <tr>
                <td>-username-</td>
                <td><input type="text" name="username" value="${param.username}"/></td>
            </tr>

            <tr>
                <td><input type="submit" value="submit"/></td>
            </tr>

        </table>

    </form>

    <br/><br/>
</div>

<div style="text-align: center">
    <c:if test="${requestScope.user != null}">
        <br/><br/>
        ${requestScope.user.username} 的权限是：
        <br/><br/>

        <form action="/authorityServlet?method=updateAuthorities" method="post">
            <input type="hidden" name="username" value="${requestScope.user.username}"/>

            <c:forEach items="${requestScope.authorities}" var="auth">
                <c:set var="flag" value="false"/>
                <c:forEach items="${user.authorities}" var="uauth">
                    <c:if test="${uauth.url == auth.url}">
                        <c:set var="flag" value="true"/>
                    </c:if>

                </c:forEach>
                <c:if test="${flag == true}">
                    <input type="checkbox" name="authority" value="${auth.url}"
                           checked="checked"/> ${auth.displayName}. ${auth.url}
                </c:if>
                <c:if test="${flag == false}">
                    <input type="checkbox" name="authority" value="${auth.url}"
                    /> ${auth.displayName}. ${auth.url}
                </c:if>
                <br/><br/>

            </c:forEach>


            <br/><br/>

            <td><input type="submit" value="update"/></td>

        </form>

    </c:if>


</div>


</body>
</html>
