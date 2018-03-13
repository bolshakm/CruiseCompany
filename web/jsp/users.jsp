<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table border="1" align="center">
    <caption>Users</caption>
    <tr>
        <th>Login</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="User" items="${Users}">
        <tr>
            <td>${User.login}</td>
            <td>${User.name}</td>
            <td>${User.lastName}</td>
            <td>${User.email}</td>
            <td>${User.role.name}</td>
            <td></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
