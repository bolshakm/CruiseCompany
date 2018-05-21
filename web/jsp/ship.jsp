<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 01:30
  To change this template use File | Settings | File Templates.
--%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="ships"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center">
    <tr>
        <td valign="top">
            <table border="1">
                <caption><fmt:message key="ships"/></caption>
                <c:if test="${user.role.id == 1}">
                    <tr align="right"><a href="CruiseCompany?command=toShipCard">
                        <button><fmt:message key="add"/></button>
                    </a></tr>
                </c:if>
                <tr>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="number"/></th>
                    <c:if test="${user.role.id == 1}">
                        <th><fmt:message key="number.of.seats"/></th>
                    </c:if>
                    <th><fmt:message key="price.per.seats"/></th>
                    <th><fmt:message key="ship.type"/></th>
                    <c:if test="${user.role.id == 3}">
                        <th colspan="2"><fmt:message key="ticket.types"/></th>
                    </c:if>
                    <c:if test="${user.role.id != 3}">
                        <th><fmt:message key="ticket.types"/></th>
                    </c:if>
                    <th><fmt:message key="bonuses"/></th>
                    <th><fmt:message key="cruises"/></th>
                    <c:if test="${user.role.id == 1}">
                        <th><fmt:message key="actions"/></th>
                    </c:if>
                </tr>
                <c:forEach var="Ship" items="${Ships}">
                <tr>
                    <td>${Ship.name}</td>
                    <td>${Ship.number}</td>
                    <c:if test="${user.role.id == 1}">
                        <td>${Ship.numberOfSeats}</td>
                    </c:if>
                    <td>${Ship.pricePerSeat}</td>
                    <td>${Ship.type.name}</td>
                    <td><c:forEach var="ticketType" items="${Ship.ticketTypes}">
                        ${ticketType.name}<br/>
                    </c:forEach>
                    </td>
                    <c:if test="${user.role.id == 3}">
                        <td><c:forEach var="ticketType" items="${Ship.ticketTypes}">
                            <a href="CruiseCompany?command=toSetBonusesForShipByTicketType&idTicketType=${ticketType.id}&idShip=${Ship.id}"><button><fmt:message key="bonuses"/></button></a><br/>
                        </c:forEach>
                        </td>
                    </c:if>
                    <c:if test="${!empty Ship.bonuses}">
                        <td><c:forEach var="bonus" items="${Ship.bonuses}">
                            ${bonus.name}<br/>
                        </c:forEach></td>
                    </c:if>
                    <c:if test="${empty Ship.bonuses}">
                        <td><fmt:message key="empty"/></td>
                    </c:if>
                    <c:if test="${!empty Ship.cruises}">
                        <td><c:forEach var="cruise" items="${Ship.cruises}">
                            <c:if test="${user.role.id == 2}"><a
                                    href="CruiseCompany?command=buyTicket&idCruise=${cruise.id}">${cruise.name}</a><br/></c:if>
                            <c:if test="${user.role.id != 2}">${cruise.name}<br/></c:if>

                        </c:forEach></td>
                    </c:if>
                    <c:if test="${empty Ship.cruises}">
                        <td>Empty</td>
                    </c:if>

                    <c:if test="${user.role.id == 1}">
                        <td>
                        <form action="CruiseCompany" method="post">
                            <input type="hidden" name="command" value="actionsForShip"/>
                            <input type="hidden" name="idShip" value="${Ship.id}"/>
                            <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                            <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                        </form>
                        </td>
                    </c:if>
                    </c:forEach>
                </tr>
            </table>
        </td>
        <c:if test="${user.role.id == 1}">
            <td width="10"></td>
            <td valign="top">
                <table border="1" bgcolor="#f0ffff">
                    <caption><fmt:message key="ship.type"/></caption>
                    <tr>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="actions"/></th>
                    </tr>
                    <c:if test="${ShipType == null}">
                        <tr>
                            <form action="CruiseCompany" method="post">
                                <td>
                                    <input type="hidden" name="command" value="addShipType">
                                    <input type="text" name="ShipTypeName">
                                </td>
                                <td>
                                    <input type="submit" name="Add" value="<fmt:message key="add"/>">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:if test="${ShipType != null}">
                        <tr>
                            <form action="CruiseCompany" method="post">
                                <td>
                                    <input type="hidden" name="command" value="updateShipType">
                                    <input type="hidden" name="ShipTypeId" value="${ShipType.id}">
                                    <input type="text" name="ShipTypeName" value="${ShipType.name}">
                                </td>
                                <td>
                                    <input type="submit" name="Update" value="<fmt:message key="update"/>">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:forEach var="ShipType" items="${ShipTypes}">
                        <tr>
                            <td>${ShipType.name}</td>
                            <td>
                                <form action="CruiseCompany" method="post">
                                    <input type="hidden" name="command" value="actionsForShipType"/>
                                    <input type="hidden" name="ShipTypeId" value="${ShipType.id}"/>
                                    <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                                    <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br/>
                <table border="1" bgcolor="#deb887">
                    <caption><fmt:message key="bonuses"/></caption>
                    <tr>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="actions"/></th>
                    </tr>
                    <c:if test="${Bonus == null}">
                        <tr>
                            <form action="CruiseCompany" method="post">
                                <td>
                                    <input type="hidden" name="command" value="addBonus">
                                    <input type="text" name="BonusName">
                                </td>
                                <td>
                                    <input type="submit" name="Add" value="<fmt:message key="add"/>">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:if test="${Bonus != null}">
                        <tr>
                            <form action="CruiseCompany" method="post">
                                <td>
                                    <input type="hidden" name="command" value="updateBonus">
                                    <input type="hidden" name="idBonus" value="${Bonus.id}">
                                    <input type="text" name="BonusName" value="${Bonus.name}">
                                </td>
                                <td>
                                    <input type="submit" name="Update" value="<fmt:message key="update"/>">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:forEach var="Bonus" items="${Bonuses}">
                        <tr>
                            <td>${Bonus.name}</td>
                            <td>
                                <form action="CruiseCompany" method="post">
                                    <input type="hidden" name="command" value="actionsForBonuses"/>
                                    <input type="hidden" name="idBonus" value="${Bonus.id}"/>
                                    <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                                    <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </c:if>
    </tr>
</table>
</body>
</html>
