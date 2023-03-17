<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/12
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>XSS Reflected</title>
</head>
<body>
<form action="/XSSReflected" method="get">
    What is your name?<input type="text" name="name"><input type="submit">
    <pre>${result}</pre>
</form>
</body>
</html>
