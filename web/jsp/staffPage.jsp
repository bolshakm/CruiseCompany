<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 19.04.2018
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="staff.page"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<table border="1" bgcolor="#fff0f5" align="center">
    <caption><fmt:message key="cruises"/></caption>
    <tr>
        <th><fmt:message key="name"/></th>
        <th><fmt:message key="from"/></th>
        <th><fmt:message key="to"/></th>
        <th><fmt:message key="ship.number"/></th>
        <th><fmt:message key="cruise.status"/></th>
        <th><fmt:message key="route"/></th>
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
