<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 01.05.2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Administrator</title>
</head>
<body>
<c:import url="header.jsp"/>
<table border="1" bgcolor="#7fffd4" align="center">
    <caption>Cruises</caption>
    <tr>
        <th>Cruise name</th>
        <th>From</th>
        <th>To</th>
        <th>Cruise status</th>
        <th>Routes</th>
        <th>Ticket/Seats</th>
    </tr>
    <c:forEach var="Cruise" items="${Cruises}">
        <tr>
            <td>${Cruise.name}</td>
            <td>${Cruise.from}</td>
            <td>${Cruise.to}</td>
            <td>${Cruise.status.name}</td>
            <td>${Cruise.route.name}</td>
            <td>${fn:length(Cruise.tickets)}/${Cruise.ship.numberOfSeats}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
