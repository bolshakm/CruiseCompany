<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="tickets"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<p align="center">${InfoMassage}</p>
<table align="center">
    <tr>
        <td valign="top">
            <table border="1" bgcolor="#f5f5dc">
                <caption><fmt:message key="tickets"/></caption>
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="login"/></th>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="last.name"/></th>
                    <th><fmt:message key="cruise.name"/></th>
                    <th><fmt:message key="cruise.from"/></th>
                    <th><fmt:message key="cruise.to"/></th>
                    <c:if test="${user.role.id == 2}">
                        <th><fmt:message key="bonuses"/></th>
                    </c:if>
                    <th><fmt:message key="ticket.types"/></th>
                    <th><fmt:message key="price"/></th>
                    <c:if test="${user.role.id == 1 || user.role.id == 2}">
                        <th><fmt:message key="actions"/></th>
                    </c:if>
                </tr>
                <c:forEach var="Ticket" items="${Tickets}" begin="${beginTickets}" end="${endTickets}">
                    <tr>
                        <td>${Ticket.id}</td>
                        <td>${Ticket.user.login}</td>
                        <td>${Ticket.name}</td>
                        <td>${Ticket.lastName}</td>
                        <td>${Ticket.cruise.name}</td>
                        <td>${Ticket.cruise.from}</td>
                        <td>${Ticket.cruise.to}</td>
                        <c:if test="${user.role.id == 2}">
                            <c:if test="${!empty Ticket.bonuses}">
                            <td><c:forEach var="Bonus" items="${Ticket.bonuses}">
                                ${Bonus.name}<br/>
                            </c:forEach> </td>
                            </c:if>
                            <c:if test="${empty Ticket.bonuses}">
                                <td><fmt:message key="empty"/></td>
                            </c:if>
                        </c:if>
                        <td>${Ticket.ticketType.name}</td>
                        <td>${Ticket.price}</td>
                        <c:if test="${user.role.id == 1 || user.role.id == 2}">
                            <td>
                                <form action="CruiseCompany" method="post">
                                    <input type="hidden" name="command" value="actionsForTicket"/>
                                    <input type="hidden" name="idTicket" value="${Ticket.id}"/>
                                    <c:if test="${user.role.id == 1}">
                                        <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                                    </c:if>
                                    <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(pageNumbersTickets)>1}">
                    <tr align="right">
                        <td colspan="11">
                            <c:forEach var="pageNumber" items="${pageNumbersTickets}">
                                <a href="CruiseCompany?command=toTicketsPage&pageNumberTickets=${pageNumber}">${pageNumber}</a>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
                </td>
                </tr>
            </table>
        </td>
        <td width="10"></td>
        <c:if test="${user.role.id == 1}">
            <td valign="top">
                <table border="1" bgcolor="#f0ffff">
                    <caption><fmt:message key="ticket.types"/></caption>
                    <tr>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="price"/></th>
                        <th><fmt:message key="actions"/></th>
                    </tr>
                    <c:if test="${TicketType == null || TicketType.id == 0}">
                        <tr>
                            <form action="CruiseCompany" method="post">
                                <td>
                                    <input type="hidden" name="command" value="addTicketType">
                                    <input type="text" size="15" name="TicketTypeName" value="${TicketType.name}">
                                </td>
                                <td><input type="text" size="10" name="pricePerTicket" value="${TicketType.price}"></td>
                                <td>
                                    <input type="submit" name="Add" value="<fmt:message key="add"/>">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:if test="${TicketType != null && TicketType.id != 0}">
                        <tr>
                            <form action="CruiseCompany" method="post">
                                <td>
                                    <input type="hidden" name="command" value="updateTicketType">
                                    <input type="hidden" name="idTicketType" value="${TicketType.id}">
                                    <input type="text" size="15" name="TicketTypeName" value="${TicketType.name}">
                                <td><input type="text" size="10" name="pricePerTicket" value="${TicketType.price}"></td>
                                </td>
                                <td>
                                    <input type="submit" name="Update" value="Update">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:forEach var="TicketType" items="${TicketTypes}" begin="${beginTicketTypes}" end="${endTicketTypes}">
                        <tr>
                            <td>${TicketType.name}</td>
                            <td>${TicketType.price}</td>
                            <td>
                                <form action="CruiseCompany" method="post">
                                    <input type="hidden" name="command" value="actionsForTicketType"/>
                                    <input type="hidden" name="idTicketType" value="${TicketType.id}"/>
                                    <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                                    <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(pageNumbersTicketTypes)>1}">
                        <tr align="right">
                            <td colspan="10">
                                <c:forEach var="pageNumber" items="${pageNumbersTicketTypes}">
                                    <a href="CruiseCompany?command=toTicketsPage&pageNumberTicketTypes=${pageNumber}">${pageNumber}</a>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:if>
                </table>
            </td>
        </c:if>
    </tr>
</table>
</body>
</html>
