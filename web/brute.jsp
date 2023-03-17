<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/3
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Brute Force</title>
</head>
<body>
<center>
    <br>
    <br>
    <br>
    <h1>BruteForce</h1>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <form action="brute" method="${brute_methods}">
        Username:<input type="text" name="username"><br>
        Password:<input type="password" name="password"><br>
        <input type="submit" value="Login">
        <input type="hidden" name="user_token" value="${user_token}">
        <h2>${result}</h2>
    </form>
</center>

</body>
</html>
