<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/10
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<head>
    <title>SQL Injection</title>
</head>
<body>
    <form action="sqlInjection" method="get">
        User ID:<input type="text" name="id">
        <input type="submit" value="Query">
    </form>
    <c:forEach items="${list}" var="map">
        <tr bgcolor="#ff7f50">
            <td>First Name:${map.first_name}</td>
            <td>Last Name: ${map.last_name}</td><br>
        </tr>
    </c:forEach>
</body>
</html>
