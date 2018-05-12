<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tickets</title>
</head>
<body>
<c:import url="header.jsp"/>
<p align="center">${InfoMassage}</p>
<table align="center">
    <tr>
        <td valign="top">
            <table border="1" bgcolor="#f5f5dc">
                <caption>Tickets</caption>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>Name</th>
                    <th>Last name</th>
                    <th>Cruise</th>
                    <th>Cruise from</th>
                    <th>Cruise to</th>
                    <c:if test="${user.role.id == 2}">
                        <th>Bonuses</th>
                    </c:if>
                    <th>Ticket type</th>
                    <th>Price</th>
                    <c:if test="${user.role.id == 1 || user.role.id == 2}">
                        <th>Actions</th>
                    </c:if>
                </tr>
                <c:forEach var="Ticket" items="${Tickets}" begin="${begin}" end="${end}">
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
                                <td>Empty</td>
                            </c:if>
                        </c:if>
                        <td>${Ticket.ticketType.name}</td>
                        <td>${Ticket.price}</td>
                        <c:if test="${user.role.id == 1 || user.role.id == 2}">
                            <td>
                                <c:if test="${user.role.id == 1}">
                                    <a href="/CruiseCompany?command=toUpdateTicket&idTicket=${Ticket.id}"><button>Update</button></a>
                                </c:if>
                                <a href="/CruiseCompany?command=deleteTicket&idTicket=${Ticket.id}"><button>Delete</button></a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <tr align="right"><td colspan="10">
                    <c:forEach var="pageNumber" items="${pageNumbers}">
                        <a href="CruiseCompany?command=toTicketsPage&pageNumber=${pageNumber}">${pageNumber}</a>
                    </c:forEach>
                </td>
                </tr>
            </table>
        </td>
        <td width="10"></td>
        <c:if test="${user.role.id == 1}">
            <td valign="top">
                <table border="1" bgcolor="#f0ffff">
                    <caption>Ticket Type</caption>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                    <c:if test="${TicketType == null}">
                        <tr>
                            <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
                                <td>
                                    <input type="hidden" name="command" value="addTicketType">
                                    <input type="text" size="15" name="TicketTypeName">
                                </td>
                                <td><input type="text" size="10" name="pricePerTicket"></td>
                                <td>
                                    <input type="submit" name="Add" value="Add">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:if test="${TicketType != null}">
                        <tr>
                            <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
                                <td>
                                    <input type="hidden" name="command" value="updateTicketType">
                                    <input type="hidden" name="idTicketType" value="${TicketType.id}">
                                    <input type="text" name="TicketTypeName" value="${TicketType.name}">
                                <td><input type="number" name="pricePerTicket" value="${TicketType.price}"></td>
                                </td>
                                <td>
                                    <input type="submit" name="Update" value="Update">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:forEach var="TicketType" items="${TicketTypes}">
                        <tr>
                            <td>${TicketType.name}</td>
                            <td>${TicketType.price}</td>
                            <td><a href="/CruiseCompany?command=toUpdateTicketType&idTicketType=${TicketType.id}"><button>Update</button></a>
                                <a href="/CruiseCompany?command=deleteTicketType&idTicketType=${TicketType.id}"><button>Delete</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </c:if>
    </tr>
</table>
</body>
</html>
