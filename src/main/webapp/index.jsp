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


<a href="/testJson" id="testJson">test json</a>


</body>
</html>
