<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<body>


welcome: ${sessionScope.username}

<br/><br/>

count on line: ${applicationScope.count}


<a href="logout.do">logout</a>

</body>
</html>
