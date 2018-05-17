<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#testJson").click(function () {
                var url = this.href;
                var args = {};
                alert("url: " + url);
                $.post(url, args, function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var id = data[i].id;
                        var lastname = data[i].lastName;

                        alert(id + " : " + lastname);
                    }

                });

                return false;
            });

        })
    </script>
</head>
<body>


<form action="/testHttpMessageConverter" method="post" >
    <input id="name" name="name" type="text"/>

    <input type="submit" value="submit"/>
</form>

</body>
</html>
