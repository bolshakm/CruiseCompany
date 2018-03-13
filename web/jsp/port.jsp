<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ports</title>
</head>
<body>
<table border="1" align="center">
    <caption>Ports</caption>
    <tr>
        <th>Name</th>
        <th>City</th>
        <th>Country</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="Port" items="${Ports}">
        <tr>
            <td>${Port.name}</td>
            <td>${Port.city}</td>
            <td>${Port.country}</td>
            <td>Actions</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
