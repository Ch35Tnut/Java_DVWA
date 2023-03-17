<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/8
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CSRF</title>
</head>
<body>
<form action="csrf" method="${brute_methods}">
    New password:<input type="text" name="new_password"><br>
    Confirm new password:<input type="text" name="con_password"><br>
    <input type="submit" value="Change">
    <input type="hidden" name="token" value="${user_token}">
    <h1>${result}</h1>
</form>
</body>
</html>
