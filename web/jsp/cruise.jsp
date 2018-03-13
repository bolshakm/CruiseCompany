<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cruises</title>
</head>
<body>
<%--<table border="1" align="center">--%>
    <%--<caption>Cruises</caption>--%>
    <%--<tr>--%>
        <%--<th>Name</th>--%>
        <%--<th>From</th>--%>
        <%--<th>To</th>--%>
        <%--&lt;%&ndash;Only for administrator&ndash;%&gt;--%>
        <%--<th>Income money</th>--%>
        <%--<th>Ship number</th>--%>
        <%--<th>Cruise status</th>--%>
        <%--<th>Ticket/Seats</th>--%>
        <%--<th>Ports</th>--%>
        <%--<th>Actions</th>--%>
    <%--</tr>--%>
    <%--<c:forEach var="Cruise" items="${Cruises}">--%>
        <%--<tr>--%>
            <%--<td>${Cruise.name}</td>--%>
            <%--<td>${Cruise.from}</td>--%>
            <%--<td>${Cruise.to}</td>--%>
            <%--&lt;%&ndash;Only for administrator&ndash;%&gt;--%>
            <%--<td>${Cruise.money}</td>--%>
            <%--<td>${Cruise.ship.number}</td>--%>
            <%--<td>${Cruise.status.name}</td>--%>
            <%--<td>${fn:length(Cruise.tickets)}/${Cruise.ship.numberOfSeats}</td>--%>
            <%--<td><c:forEach var="port" items="${Cruise.ports}">--%>
                <%--${port.name}<br/>--%>
            <%--</c:forEach></td>--%>
            <%--<td></td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
<%--</table>--%>
<table align="center">
    <tr>
        <td>
            <table border="1" bgcolor="#7fffd4">
                <caption>Cruises</caption>
                <tr>
                    <th>Name</th>
                    <th>From</th>
                    <th>To</th>
                    <%--Only for administrator--%>
                    <th>Income money</th>
                    <th>Ship number</th>
                    <th>Cruise status</th>
                    <th>Ticket/Seats</th>
                    <th>Ports</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="Cruise" items="${Cruises}">
                    <tr>
                        <td>${Cruise.name}</td>
                        <td>${Cruise.from}</td>
                        <td>${Cruise.to}</td>
                            <%--Only for administrator--%>
                        <td>${Cruise.money}</td>
                        <td>${Cruise.ship.number}</td>
                        <td>${Cruise.status.name}</td>
                        <td>${fn:length(Cruise.tickets)}/${Cruise.ship.numberOfSeats}</td>
                        <td><c:forEach var="port" items="${Cruise.ports}">
                            ${port.name}<br/>
                        </c:forEach></td>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td width="100">
            <table border="1" bgcolor="#f0ffff">
                <caption>Cruise Status</caption>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="CruiseStatus" items="${CruiseStatuses}">
                    <tr>
                        <td>${CruiseStatus.name}</td>
                        <td>Actions</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
