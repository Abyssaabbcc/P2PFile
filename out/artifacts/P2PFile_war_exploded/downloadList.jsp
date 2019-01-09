<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>downloadList</title>
</head>
<body>
<h1>点击下载文件</h1>
<br>
<c:forEach var="address" items="${list}">
    <c:forEach var="filename" items="${address.value}">
        <a href="http://${address.key}/file/download?filename=${filename}">${filename}</a>
        <br>
    </c:forEach>
</c:forEach>
<br>
<a href="/upload.jsp">文件上传</a>
</body>
</html>

