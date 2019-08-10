<%--
  Created by IntelliJ IDEA.
  User: always_on_the_way
  Date: 2019/6/26
  Time: 上午11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="/loginShiro" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="submit" value="登录">
</form>
</body>
</html>
