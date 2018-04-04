<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 11.09.2017
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />--%>
<%--<fmt:setLocale value="${language}" />--%>
<%--<fmt:setBundle basename="text" />--%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<p align="center">Welcome ${user.name}</p>
<p align="right"> ${user.login}| <a href="CruiseCompany?command=logout">Logout</a></p>
<table align="center">
    <tr>
        <td valign="top">
            <table border="1" align="center">
                <caption>Cruises</caption>
                <tr>
                    <th>Name</th>
                    <th>From - To</th>
                    <%--Only for administrator--%>
                    <th>Ship name (number)</th>
                    <th>Ports</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="Cruise" items="${Cruises}">
                <tr bgcolor="#7fffd4">
                    <td height="50px">${Cruise.name}</td>
                    <td>From ${Cruise.from} to ${Cruise.to}</td>
                    <td>${Cruise.ship.name} (${Cruise.ship.number})</td>
                        <%--<td>${fn:length(Cruise.tickets)}/${Cruise.ship.numberOfSeats}</td>--%>
                    <td><c:forEach var="port" items="${Cruise.ports}">
                        ${port.name},
                    </c:forEach></td>
                    <td>${Cruise.status.name}</td>
                    <td>Price: ${Cruise.ship.pricePerSeat + TicketStandardPrice.price}</td>
                    <td>actions</td>
                    </c:forEach>
                </tr>
            </table>
        </td>
        <td width="100px"></td>
        <td valign="top">
            <table border="1">
                <caption>Ships</caption>
                <tr>
                    <th>Name(Number)</th>
                    <th>Ship type</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="Cruise" items="${Ships}">
                    <tr>
                        <td>${Cruise.name}(${Cruise.number})</td>
                        <td>${Cruise.type.name}</td>
                        <td>actions</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
