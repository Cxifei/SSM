<%--
  Created by IntelliJ IDEA.
  User: always_on_the_way
  Date: 2019/6/27
  Time: 上午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="/fileUpload" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="上传文件">
    </form>

    <form action="/fileUpload2" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="上传文件">
    </form>

    <form action="/fileUploadMulti" method="post" enctype="multipart/form-data">
        <input type="file" name="files" multiple="multiple">
        <input type="submit" value="上传文件">
    </form>

    <form action="/fileUpload3" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="file" name="file1">
        <input type="file" name="file2">
        <input type="file" name="file3">
        <input type="file" name="file4">
        <input type="submit" value="上传文件">
    </form>

</body>
</html>
