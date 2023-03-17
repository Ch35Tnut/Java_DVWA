<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/12
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
    <title>XSS Stored</title>
</head>
<body>
<form method="post"  action="/XSSStored">
<table width="550" border="0" cellpadding="2" cellspacing="1">
    <tbody><tr>
        <td width="100">Name *</td>
        <td><input name="name" type="text" size="30" maxlength="10"></td>
    </tr>
    <tr>
        <td width="100">Message *</td>
        <td><textarea name="message" cols="50" rows="3" maxlength="50"></textarea></td>
    </tr>
    <tr>
        <td width="100">&nbsp;</td>
        <td>
            <input type="submit" value="Sign Guestbook">
            <a href="/XSSStored"><button type="button" >ClearGuestbook</button></a>
        </td>
    </tr>
    </tbody></table>

</form>
<table>
    <c:forEach items="${list}" var="guestbook">
        <tr bgcolor="#ff7f50">
            <td>First Name:${guestbook.name}</td>
            <td>Last Name: ${guestbook.comment}</td><br>
        </tr>
    </c:forEach>
</table>

</body>
</html>
