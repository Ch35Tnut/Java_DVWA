<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/9
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CSRF Impossible</title>
</head>
<body>

<form action="csrf_imp" method="post">
    Original password:<input type="text" name="ori_password"><br>
    New password:<input type="text" name="new_password"><br>
    Confirm new password:<input type="text" name="con_password"><br>
    <input type="submit" value="Change">
    <input type="hidden" name="token" value="${user_token}">
    <h1>${result}</h1>
</form>
</body>
</html>
