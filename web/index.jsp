<%--
  Created by IntelliJ IDEA.
  User: nullguo
  Date: 2019/1/2 0002
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>P2P文件分享系统启动页</title>
  </head>
  <body>
  <form action="/init/init" method="GET">
    本地地址: <input type="text" name="localAddress">
    <br />
    外地地址: <input type="text" name="remoteAddress" />
    <input type="submit" value="启动" />
  </form>
  </body>
</html>
