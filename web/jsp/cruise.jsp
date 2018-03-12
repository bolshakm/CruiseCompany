<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cruises</title>
</head>
<body>
<table border="1" align="center">
    <caption>Cruises</caption>
    <tr>
        <th>Name</th>
        <th>From</th>
        <th>To</th>
        <%--Only for administrator--%>
        <th>Income money</th>
        <th>Ship number</th>
        <th>Cruise status</th>
        <th>Ticket/Seats</th>
        <th>Ports</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="Cruise" items="${Cruises}">
        <tr>
            <td>${Cruise.name}</td>
            <td>${Cruise.from}</td>
            <td>${Cruise.to}</td>
            <%--Only for administrator--%>
            <td>${Cruise.money}</td>
            <td>${Cruise.ship.number}</td>
            <td>${Cruise.status.name}</td>
            <td>${fn:length(Cruise.tickets)}/${Cruise.ship.numberOfSeats}</td>
            <td><c:forEach var="port" items="${Cruise.ports}">
                ${port.name}<br/>
            </c:forEach></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
