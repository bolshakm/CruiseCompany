<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 11.09.2017
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<c:import url="header.jsp"/>
<table border="1" bgcolor="#7fffd4" align="center">
    <caption><fmt:message key="cruises"/></caption>
    <tr>
        <th><fmt:message key="name"/></th>
        <th><fmt:message key="from"/></th>
        <th><fmt:message key="to"/></th>
        <th><fmt:message key="ship.number"/></th>
        <th><fmt:message key="cruise.status"/></th>
        <th><fmt:message key="ticket.seats"/></th>
        <th><fmt:message key="route"/></th>
        <th><fmt:message key="actions"/></th>
    </tr>
    <c:forEach var="Cruise" items="${Cruises}">
        <tr>
            <td>${Cruise.name}</td>
            <td>${Cruise.from}</td>
            <td>${Cruise.to}</td>
            <td>${Cruise.ship.number}</td>
            <td>${Cruise.status.name}</td>
            <td>${fn:length(Cruise.tickets)}/${Cruise.ship.numberOfSeats}</td>
            <td>${Cruise.route.name}</td>
            <td><a href="CruiseCompany?command=buyTicket&idCruise=${Cruise.id}"><button><fmt:message key="buy"/></button></a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
