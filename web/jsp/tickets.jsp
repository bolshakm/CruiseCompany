<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


<table align="center">
    <tr>
        <td>
            <table border="1" bgcolor="#f5f5dc">
                <caption>Tickets</caption>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Last name</th>
                    <th>Cruise</th>
                    <th>Cruise from</th>
                    <th>Cruise to</th>
                    <th>Ticket type</th>
                    <th>Price</th>

                    <th>Actions</th>
                </tr>
                <c:forEach var="Ticket" items="${Tickets}">
                    <tr>
                        <td>${Ticket.id}</td>
                        <td>${Ticket.user.name}</td>
                        <td>${Ticket.user.lastName}</td>
                        <td>${Ticket.cruise.name}</td>
                        <td>${Ticket.cruise.from}</td>
                        <td>${Ticket.cruise.to}</td>
                        <td>${Ticket.ticketType.name}</td>
                        <td>${Ticket.price}</td>
                        <td>Actions</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td width="100"></td>
        <td>
            <table border="1" bgcolor="#f0ffff">
                <caption>Ticket Type</caption>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="TicketType" items="${TicketTypes}">
                    <tr>
                        <td>${TicketType.name}</td>
                        <td>Actions</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
