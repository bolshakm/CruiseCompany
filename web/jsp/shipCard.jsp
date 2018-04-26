<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 30.03.2018
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Card</title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption>Ship Card</caption>
    <tr>
        <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
            <c:if test="${idShip == null}"><input type="hidden" name="command" value="addShip"/></c:if>
                <c:if test="${idShip != null}"><input type="hidden" name="command" value="updateShip"/></c:if>
                <input type="hidden" name="idShip" value="${idShip}">

    <td>Name</td>
    <td><input type="text" name="name" value="${name}"/></td>
    </tr>
    <tr>
        <td>Number</td>
        <td><input type="text" name="shipNumber" value="${shipNumber}"/></td>
    </tr>
    <tr>
        <td>Number of seats</td>
        <td><input type="number" name="numberOfSeats" value="${numberOfSeats}"/></td>
    </tr>
    <tr>
        <td>Price per seat</td>
        <td><input type="number" name="pricePerSeat" value="${pricePerSeat}"/></td>
    </tr>
    <tr>
        <td>Ship type</td>
        <td><select name="idShipType">
            <c:if test="${idShip == null}">
                <option disabled selected>Select ship type</option>
            </c:if>
            <c:forEach var="ShipType" items="${ShipTypes}">
                <c:if test="${ShipType.id == idShipType}">
                    <option selected value="${ShipType.id}">${ShipType.name}</option>
                </c:if>
                <c:if test="${ShipType.id != idShipType}">
                    <option value="${ShipType.id}">${ShipType.name}</option>
                </c:if>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td>Ticket types</td>
        <td>
            <c:forEach var="TicketType" items="${TicketTypes}">
                <c:if test="${fn:contains(selectedTicketTypes, TicketType)}">
                    <input type="checkbox" checked name="selectedTicketTypes" value="${TicketType.id}">${TicketType.name}<br/>
                </c:if>
                <c:if test="${!fn:contains(selectedTicketTypes, TicketType)}">
                    <input type="checkbox" name="selectedTicketTypes" value="${TicketType.id}">${TicketType.name}<br/>
                </c:if>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td>Bonuses</td>
        <td>
            <c:forEach var="Bonus" items="${Bonuses}">
                <c:if test="${fn:contains(selectedBonuses, Bonus)}">
                    <input type="checkbox" checked name="selectedBonuses" value="${Bonus.id}">${Bonus.name}<br/>
                </c:if>
                <c:if test="${!fn:contains(selectedBonuses, Bonus)}">
                    <input type="checkbox" name="selectedBonuses" value="${Bonus.id}">${Bonus.name}<br/>
                </c:if>
            </c:forEach>
        </td>
    </tr>
    <tr><td></td><td><c:if test="${idShip == null}"><input type="submit" value="Add"></c:if>
        <c:if test="${idShip != null}"><input type="submit" value="Update"></c:if></td></tr>
    </form>
    </tr>
</table>
</body>
</html>
