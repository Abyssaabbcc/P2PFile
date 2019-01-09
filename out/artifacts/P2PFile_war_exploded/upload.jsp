<%--
  Created by IntelliJ IDEA.
  User: nullguo
  Date: 2019/1/2 0002
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<body>
<form action="/file/upload" method="post" enctype="multipart/form-data">
    <h2>文件上传</h2>
    文件:<input type="file" name="uploadFile"/><br/><br/>
    <input type="submit" value="上传"/>
</form>
<br>
<a href="/file/downloadList">文件下载列表</a>
</body>
</body>
</html>
