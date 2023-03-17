<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/3
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DVWA:Login</title>
    <link href="css/login.css" type="text/css" rel="stylesheet">
</head>
<body>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<center>
    <form action="login" method="post">
        Username:<input type="text" name="username"><br>
        Password:<input type="password" name="password"><br>
        Security Level:<input type="radio" name="level" value="low" checked="checked"> Low
                       <input type="radio" name="level" value="medium"> Medium
                       <input type="radio" name="level" value="high"> High
                       <input type="radio" name="level" value="impossible">Impossible<br>
        <input type="submit" value="Login">
    </form>
</center>
</body>
</html>
