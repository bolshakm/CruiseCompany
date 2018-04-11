<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 11.09.2017
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />--%>
<%--<fmt:setLocale value="${language}" />--%>
<%--<fmt:setBundle basename="text" />--%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<p align="center">Welcome ${user.name}</p>
<p align="center"> <a href="/CruiseCompany?command=toUpdateUserCard&idUser=${user.id}">${user.login}</a>| <a href="CruiseCompany?command=logout">Logout</a></p><br/>
<table border="1" bgcolor="#7fffd4" align="center">
    <caption>Cruises</caption>
    <tr>
        <th>Name</th>
        <th>From</th>
        <th>To</th>
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
            <td>${Cruise.ship.number}</td>
            <td>${Cruise.status.name}</td>
            <td>${fn:length(Cruise.tickets)}/${Cruise.ship.numberOfSeats}</td>
            <td><c:forEach var="port" items="${Cruise.ports}">
                ${port.name}<br/>
            </c:forEach></td>
            <td>actions</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
