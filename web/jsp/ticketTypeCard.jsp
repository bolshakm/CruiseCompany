<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 24.04.2018
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket Type Card</title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption>Ticket Type Card</caption>
    <tr>
        <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
            <c:if test="${TicketType == null}"><input type="hidden" name="command" value="addTicketType"/></c:if>
            <c:if test="${TicketType != null}"><input type="hidden" name="command" value="updateTicketType"/></c:if>
            <input type="hidden" name="idTicketType" value="${TicketType.id}">

            <td>Name</td>
            <td><input type="text" name="name" value="${name}"/></td>
    </tr>
    <tr>
        <td>Bonuses</td>
        <td><c:forEach var="Bonus" items="${Bonuses}">
            <c:if test="${fn:contains(TicketType.bonuses, Bonus)}">
                <input type="checkbox" checked name="selectedExcursions"
                       value="${Bonus.id}">${Bonus.name}<br/>
            </c:if>
            <c:if test="${!fn:contains(TicketType.bonuses, Bonus)}">
                <input type="checkbox" name="selectedExcursions"
                       value="${Bonus.id}">${Bonus.name}
                <br/>
            </c:if>
        </c:forEach></td>
    </tr>
    <tr><td></td><td><c:if test="${TicketType == null}"><input type="submit" value="Add"></c:if>
        <c:if test="${TicketType != null}"><input type="submit" value="Update"></c:if></td></tr>
    </form>
    </tr>
</table>
</body>
</html>
