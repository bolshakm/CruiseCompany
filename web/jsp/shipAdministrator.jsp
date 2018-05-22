<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 01.05.2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<html>
<head>
    <title><fmt:message key="ship.administrator"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<table border="1" bgcolor="#7fffd4" align="center">
    <caption><fmt:message key="cruises"/></caption>
    <tr>
        <th><fmt:message key="cruise.name"/></th>
        <th><fmt:message key="from"/></th>
        <th><fmt:message key="to"/></th>
        <th><fmt:message key="cruise.status"/></th>
        <th><fmt:message key="routes"/></th>
        <th><fmt:message key="ticket.seats"/></th>
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
