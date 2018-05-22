<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 30.03.2018
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="ship.card"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption><fmt:message key="ship.card"/></caption>
    <tr>
        <form action="CruiseCompany" method="post">
            <c:if test="${Ship.id == null || Ship.id == 0}"><input type="hidden" name="command" value="addShip"/></c:if>
                <c:if test="${Ship.id != null && Ship.id != 0}"><input type="hidden" name="command" value="updateShip"/></c:if>
                <input type="hidden" name="idShip" value="${Ship.id}">

    <td><fmt:message key="name"/></td>
    <td><input type="text" name="name" value="${Ship.name}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="number"/></td>
        <td><input type="text" name="shipNumber" value="${Ship.number}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="number.of.seats"/></td>
        <td><input type="number" name="numberOfSeats" value="${Ship.numberOfSeats}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="price.per.seats"/></td>
        <td><input type="text" name="pricePerSeat" value="${Ship.pricePerSeat}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="ship.type"/>e</td>
        <td><select name="idShipType">
            <c:if test="${Ship.id == null || Ship.id == 0}">
                <option disabled selected><fmt:message key="select.ship.type"/></option>
            </c:if>
            <c:forEach var="ShipType" items="${ShipTypes}">
                <c:if test="${ShipType.id == Ship.type.id}">
                    <option selected value="${ShipType.id}">${ShipType.name}</option>
                </c:if>
                <c:if test="${ShipType.id != Ship.type.id}">
                    <option value="${ShipType.id}">${ShipType.name}</option>
                </c:if>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td><fmt:message key="ticket.types"/></td>
        <td>
            <c:forEach var="TicketType" items="${TicketTypes}">
                <c:if test="${fn:contains(Ship.ticketTypes, TicketType)}">
                    <input type="checkbox" checked name="selectedTicketTypes" value="${TicketType.id}">${TicketType.name}<br/>
                </c:if>
                <c:if test="${!fn:contains(Ship.ticketTypes, TicketType)}">
                    <input type="checkbox" name="selectedTicketTypes" value="${TicketType.id}">${TicketType.name}<br/>
                </c:if>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td><fmt:message key="bonuses"/></td>
        <td>
            <c:forEach var="Bonus" items="${Bonuses}">
                <c:if test="${fn:contains(Ship.bonuses, Bonus)}">
                    <input type="checkbox" checked name="selectedBonuses" value="${Bonus.id}">${Bonus.name}<br/>
                </c:if>
                <c:if test="${!fn:contains(Ship.bonuses, Bonus)}">
                    <input type="checkbox" name="selectedBonuses" value="${Bonus.id}">${Bonus.name}<br/>
                </c:if>
            </c:forEach>
        </td>
    </tr>
    <tr><td></td><td><c:if test="${Ship.id == null || Ship.id == 0}"><input type="submit" value="<fmt:message key="add"/>"></c:if>
        <c:if test="${Ship.id != null && Ship.id != 0}"><input type="submit" value="<fmt:message key="update"/>"></c:if></td></tr>
    </form>
    </tr>
</table>
</body>
</html>
