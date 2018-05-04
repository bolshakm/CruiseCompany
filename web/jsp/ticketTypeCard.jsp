<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 02.05.2018
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket type card</title>
</head>
<body>
<c:import url="header.jsp"/>
<form action="CruiseCompany" method="post">
    <input type="hidden" name="command" value="editBonuses">
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption>Ticket type card</caption>
        <tr>
            <td>Ship number</td>
            <td>${Ship.number}</td>
            <input type="hidden" name="idShip" value="${Ship.id}">
        </tr>
    <tr>
        <td>Ticket type</td>
        <td>${TicketType.name}</td>
        <input type="hidden" name="idTicketType" value="${TicketType.id}">
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
    <tr><td></td><td><input type="submit" value="Edit"></td></tr>
</table>
</form>
</body>
</html>
