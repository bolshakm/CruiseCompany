<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 22.04.2018
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route card</title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption>Route Card</caption>
    <tr>
        <form action="CruiseCompany" method="post">
            <c:if test="${Route.id == null || Route.id == 0}"><input type="hidden" name="command" value="addRoute"/></c:if>
            <c:if test="${Route.id != null && Route.id != 0}"><input type="hidden" name="command" value="updateRoute"/></c:if>
            <input type="hidden" name="idRoute" value="${Route.id}">

            <td>Name</td>
            <td><input type="text" name="name" value="${Route.name}"/></td>
    </tr>
    <tr>
        <td>Ports</td>
        <td>
            <c:forEach var="Port" items="${Ports}">
                <c:if test="${fn:contains(Route.ports, Port)}">
                    <input type="checkbox" checked name="selectedPorts" value="${Port.id}">${Port.name}<br/>
                </c:if>
                <c:if test="${!fn:contains(Route.ports, Port)}">
                    <input type="checkbox" name="selectedPorts" value="${Port.id}">${Port.name}<br/>
                </c:if>
            </c:forEach>
        </td>
    </tr>
    <tr><td></td><td><c:if test="${Route.id == null || Route.id == 0}"><input type="submit" value="Add"></c:if>
        <c:if test="${Route.id != null && Route.id != 0}"><input type="submit" value="Update"></c:if></td></tr>
    </form>
    </tr>
</table>
</body>
</html>
