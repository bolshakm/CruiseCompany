<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 03.04.2018
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket Card</title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption>Ticket #${idTicket}</caption>
    <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
        <input type="hidden" name="command" value="updateTicket"/>
        <tr>
            <input type="hidden" name="idTicket" value="${idTicket}">
            <td>Login</td>
            <td>${login}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="${name}"/></td>
        </tr>
        <tr>
            <td>Last name</td>
            <td><input type="text" name="lastName" value="${lastName}"/></td>
        </tr>
        <tr>
            <td>Cruise</td>
            <td>
                <select name="CruiseId">
                    <c:forEach var="Ship" items="${Cruises}">
                        <c:if test="${Ship.id == idCruise}">
                            <option selected value="${Ship.id}">${Ship.name}</option>
                        </c:if>
                        <c:if test="${Ship.id != idCruise}">
                            <option value="${Ship.id}">${Ship.name}</option>
                        </c:if>
                    </c:forEach>
                </select></td>
        </tr>
        <tr>
            <td>Ticket type</td>
            <td>
                <select name="TicketTypeId">
                    <c:forEach var="TicketType" items="${TicketTypes}">
                        <c:if test="${TicketType.id == idTicketType}">
                            <option selected value="${TicketType.id}">${TicketType.name}</option>
                        </c:if>
                        <c:if test="${TicketType.id != idTicketType}">
                            <option value="${TicketType.id}">${TicketType.name}</option>
                        </c:if>
                    </c:forEach>
                </select></td>
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
        <tr>
            <td>Excursions</td>
            <td>
                <c:forEach var="Excursion" items="${Excursions}">
                    <c:if test="${fn:contains(selectedExcursions, Excursion)}">
                        <input type="checkbox" checked name="selectedExcursions" value="${Excursion.id}">${Excursion.name}<br/>
                    </c:if>
                    <c:if test="${!fn:contains(selectedExcursions, Excursion)}">
                        <input type="checkbox" name="selectedExcursions" value="${Excursion.id}">${Excursion.name}<br/>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update"></td>
        </tr>
    </form>
</table>
</body>
</html>
