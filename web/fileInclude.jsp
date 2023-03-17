<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/9
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Include</title>
</head>
<body>
<a href="file?page=file1.jsp">File1.jsp</a>
<%@ include file="file/file1.jsp"%>
<form action="include" method="get">
    <input type="submit" name="file" value="file1.jsp">
</form>
</body>
</html>
