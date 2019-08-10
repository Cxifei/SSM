<%--
  Created by IntelliJ IDEA.
  User: always_on_the_way
  Date: 2019/6/26
  Time: 下午2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>

<table style="border: 1px">
    <thead>
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th>联系方式</th>
        <th>家庭住址</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>${user.id}</th>
        <th>${user.name}</th>
        <th>${user.age}</th>
        <th>${user.sex}</th>
        <th>${user.telephone}</th>
        <th>${user.address}</th>
    </tr>
    </tbody>
</table>

</body>
</html>
