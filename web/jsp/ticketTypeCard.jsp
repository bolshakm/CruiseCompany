<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 02.05.2018
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="ticket.type.card"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<c:import url="header.jsp"/>
<form action="CruiseCompany" method="post">
    <input type="hidden" name="command" value="editBonuses">
    <div class="table-ticket-type-card">
    <table align="center" border="1px ">
        <div class="header"><fmt:message key="ticket.type.card"/></div>
        <tr>
            <td><fmt:message key="ship.number"/></td>
            <td>${Ship.number}</td>
            <input type="hidden" name="idShip" value="${Ship.id}">
        </tr>
    <tr>
        <td><fmt:message key="ticket.type"/></td>
        <td>${TicketType.name}</td>
        <input type="hidden" name="idTicketType" value="${TicketType.id}">
    </tr>
    <tr>
        <td><fmt:message key="bonuses"/></td>
        <c:if test="${empty Bonuses}">
            <td><fmt:message key="empty"/></td>
        </c:if>
        <c:if test="${!empty Bonuses}">
        <td>
            <c:forEach var="Bonus" items="${Bonuses}">
                <c:if test="${fn:contains(selectedBonuses, Bonus)}">
                    <input type="checkbox" checked name="selectedBonuses" value="${Bonus.id}">${Bonus.name}<br/>
                </c:if>
                <c:if test="${!fn:contains(selectedBonuses, Bonus)}">
                    <input type="checkbox" name="selectedBonuses" value="${Bonus.id}">${Bonus.name}<br/>
                </c:if>
            </c:forEach>
            </c:if>
        </td>
    </tr>
    <tr><td></td><c:if test="${empty Bonuses}"><td><input type="submit" value="<fmt:message key="back"/>"></td></c:if>
        <c:if test="${!empty Bonuses}"><td><input type="submit" value="<fmt:message key="edit"/>"></td></c:if></tr>
</table>
    </div>
</form>
</body>
</html>
