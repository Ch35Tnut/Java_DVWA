<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/7
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Command Injection</title>
</head>
<body>
<center>
    <br>
    <br>
    <br>
    <h1>Command Injection</h1>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <form action="/command" method="post">
        Ping:<input type="text" name="ip"> <input type="submit" value="submit">
        <h2>${result}</h2>
    </form>
</center>
</body>
</html>
