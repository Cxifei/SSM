<%--
  Created by IntelliJ IDEA.
  User: always_on_the_way
  Date: 2019/6/28
  Time: 上午10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/register" method="post">
        <input type="text" name="username">
        <input type="password" name="password">
        <input type="submit" value="注册">
    </form>

<shiro:guest>
    <a href="/loginShiro">登录</a>
</shiro:guest>

<shiro:authenticated>
    欢迎你：<shiro:principal property="username"/>
</shiro:authenticated>

<shiro:hasRole name="admin">你有admin的角色</shiro:hasRole>
<shiro:hasRole name="role">你有role的角色</shiro:hasRole>

<shiro:hasPermission name="info">你有info的权限</shiro:hasPermission>

</body>
</html>
