<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 19.04.2018
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff Page</title>
</head>
<body>
<c:import url="header.jsp"/>
<table border="1" bgcolor="#fff0f5" align="center">
    <caption>Cruises</caption>
    <tr>
        <th>Name</th>
        <th>From</th>
        <th>To</th>
        <th>Ship number</th>
        <th>Cruise status</th>
        <th>Route</th>
    </tr>
    <c:forEach var="Cruise" items="${Cruises}">
        <tr>
            <td>${Cruise.name}</td>
            <td>${Cruise.from}</td>
            <td>${Cruise.to}</td>
            <td>${Cruise.ship.number}</td>
            <td>${Cruise.status.name}</td>
            <td>${Cruise.route.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
