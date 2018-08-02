<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传</title>
    <script type="text/javascript" src="/scripts/jquery-1.7.2.js"></script>

    <script type="text/javascript">
        $(function () {
            var i = 2;
            $("#addFile").click(function () {
                $(this).parent().parent().before(
                    '<tr class="file"><td>File' + i + ':</td><td><input type="file" name="file' + i + '"/></td></tr>' +
                    '<tr class="desc"><td>Desc' + i + ':</td><td><input type="text" name="desc' + i + '"/><button id="delete' + i + '">删除</button></td></tr>'
                );

                i++;
                //获取新添加的删除按钮
                $("#delete" + (i - 1)).click(function () {
                    var $tr = $(this).parent().parent();
                    $tr.prev("tr").remove();
                    $tr.remove();

                    // 对i进行重新排序，这个就是删除了中间的某一个的时候，这个数字还是按照顺序的
                    $(".file").each(function (index) {
                        var n = index + 1;
                        $(this).find("td:first").text("File" + (n));
                        $(this).find("td:last input").attr("name", "file" + (n));
                    });
                    $(".desc").each(function (index) {
                        var n = index + 1;
                        $(this).find("td:first").text("Desc" + (n));
                        $(this).find("td:last input").attr("name", "desc" + (n));
                    });


                    i--;
                });

                return false;
            });

        });


    </script>
</head>
<body>

<font color="#663399">${message}</font>
<br/><br/>

<form action="upLoadServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" id="fileNum" name="fileNum" value="1">
    <br/><br/>

    <table>
        <tr class="file">
            <td>File1 : </td>
            <td><input type="file" name="file"/></td>
        </tr>
        <tr class="desc">
            <td>Desc1 : </td>
            <td><input type="text" name="desc"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="submit" value="submit"/>
            </td>
            <td>
                <!-- 按钮 需要js来支持 -->
                <button id="addFile">新增一个附件</button>
            </td>
        </tr>
    </table>

    <br/><br/>

</form>


</body>
</html>
