<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>

<div id="contentBox">

    <h1>Shiro Login</h1>

    <p>
        <span style="color: red;">
                ${errorInvalidLogin}
        </span>
    </p>

    <form action="login" method="POST">
        Username: <input id="username" name="username" type="text"/><br/><br/>
        Password: <input name="password" type="password"/><br/><br/>
        <input type="submit" value="Login"/>
    </form>

    <p>Try logging in with username/passwords: user1/user1 and user2/user2.</p>
</div>
</body>
</html>
