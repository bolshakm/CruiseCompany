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
    <c:if test="${Ticket != null}">
        <caption>Ticket #${Ticket.id}</caption>
    </c:if>
    <form action="CruiseCompany" method="post">
        <c:if test="${Ticket != null && Ticket.id != 0}">
            <input type="hidden" name="command" value="updateTicket"/>
        </c:if>
        <c:if test="${Ticket == null || Ticket.id == 0}">
            <c:if test="${Ticket.price == null}">
                <input type="hidden" name="command" value="getPrice"/>
            </c:if>
            <c:if test="${Ticket.price != null}">
                <input type="hidden" name="command" value="addTicket"/>
            </c:if>
        </c:if>
        <tr>
            <input type="hidden" name="idTicket" value="${Ticket.id}">
            <td>Login</td>
            <td>${Ticket.user.login}
                <input type="hidden" name="login" value="${Ticket.user.login}">
            </td>
        </tr>
        <tr>
            <td>Name</td>
            <td>
                <c:if test="${Ticket.price == null || user.role.id == 1}">
                    <input type="text" name="name" value="${Ticket.name}"/>
                </c:if>
                <c:if test="${Ticket.price != null && user.role.id != 1}">
                    ${Ticket.name}
                    <input type="hidden" name="name" value="${Ticket.name}">
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Last name</td>
            <td>
                <c:if test="${Ticket.price == null || user.role.id == 1}">
                    <input type="text" name="lastName" value="${Ticket.lastName}"/>
                </c:if>
                <c:if test="${Ticket.price != null && user.role.id != 1}">
                    ${Ticket.lastName}
                    <input type="hidden" name="lastName" value="${Ticket.lastName}">
                </c:if>
            </td>
        </tr>
<c:if test="${user.role.id != 1}">
        <tr>
            <td>Cruise</td>
                <td>${cruise.name}</td>
                <input type="hidden" name="idCruise" value="${cruise.id}">
        </tr>
</c:if>
        <c:if test="${user.role.id != 1}">
        <tr>
            <td>Cruise from</td>
                <td>${cruise.from}</td>
        </tr>
        </c:if>
        <c:if test="${user.role.id != 1}">
            <tr>
                <td>Cruise to</td>
                <td>${cruise.to}</td>
            </tr>
        </c:if>
        <tr>
            <td>Ticket type</td>
            <td>
                <c:if test="${Ticket.price == null || user.role.id == 1}">
                    <select name="TicketTypeId">
                        <c:forEach var="TicketType" items="${TicketTypes}">
                            <c:if test="${TicketType.id == Ticket.ticketType.id}">
                                <option selected value="${TicketType.id}">${TicketType.name}</option>
                            </c:if>
                            <c:if test="${TicketType.id != Ticket.ticketType.id}">
                                <option value="${TicketType.id}">${TicketType.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${Ticket.price != null && user.role.id != 1}">
                    ${Ticket.ticketType.name}
                    <input type="hidden" name="selectedTicketTypeId" value="${Ticket.ticketType.id}">
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Excursions</td>
            <td>
                <c:if test="${Ticket.price == null || user.role.id == 1}">
                    <c:forEach var="Excursion" items="${Excursions}">
                        <c:if test="${fn:contains(Ticket.excursions, Excursion)}">
                    <input type="checkbox" checked name="selectedExcursions"
                           value="${Excursion.id}">${Excursion.name}<br/>
                </c:if>
                <c:if test="${!fn:contains(Ticket.excursions, Excursion)}">
                    <input type="checkbox" name="selectedExcursions" value="${Excursion.id}">${Excursion.name}
                    <br/>
                </c:if>
                </c:forEach>
                </c:if>
                <c:if test="${Ticket.price != null && user.role.id != 1}">
                    <c:forEach var="Excursion" items="${Excursions}">
                        <input type="hidden" name="selectedExcursions" value="${Excursion.id}">${Excursion.name}<br/>
                    </c:forEach>

                </c:if>
            </td>
        </tr>
        <c:if test="${!empty Bonuses}">
        <tr>
            <td>Bonuses</td>
            <td><c:forEach var="Bonus" items="${Bonuses}">
                ${Bonus.name}<br/>
            </c:forEach>
            </td>
        </tr>
        </c:if>
        <c:if test="${Ticket.price != null}">

        <tr>
            <td>Price</td>
            <td>${Ticket.price}</td>
        </tr>
        </c:if>
        <tr>
            <td></td>
            <td>
                <c:if test="${Ticket != null && Ticket.id != 0}">
                    <input type="submit" value="Update">
                </c:if>
                <c:if test="${Ticket == null || Ticket.id == 0}">
                    <c:if test="${Ticket.price == null}">
                        <input type="submit" value="Get Price">
                    </c:if>
                    <c:if test="${Ticket.price != null}">
                        <input type="submit" value="Buy">
                    </c:if>
                </c:if></td>
        </tr>
    </form>
</table>
</body>
</html>
