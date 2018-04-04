<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 31.03.2018
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cruise Card</title>
</head>
<body>
<p align="center">Hello ${user.name}</p>
<c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption>Cruise Card</caption>
        <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
            <c:if test="${idCruise == null}"><input type="hidden" name="command" value="addCruise"/></c:if>
            <c:if test="${idCruise != null}"><input type="hidden" name="command" value="updateCruise"/></c:if>
            <tr>
            <input type="hidden" name="idCruise" value="${idCruise}">

            <td>Name</td>
            <td><input type="text" name="name" value="${name}"/></td>
    </tr>
    <tr>
        <td>From</td>
        <td><input type="date" name="from" value="${from}"/></td>
    </tr>
    <tr>
        <td>To</td>
        <td><input type="date" name="to" value="${to}"/></td>
    </tr>
    <tr>
        <td>Ship </td>
        <td>
            <select name="ShipId">
            <c:if test="${idCruise == null}">
                <option disabled selected>Select ship</option>
            </c:if>
            <%--<c:if test="${idCruise != null}">--%>
                <%--<option disabled>Select ship</option>--%>
            <%--</c:if>--%>

            <c:forEach var="Cruise" items="${Ships}">
                <c:if test="${Cruise.id == idShip}">
                    <option selected value="${Cruise.id}">${Cruise.name}</option>
                </c:if>
                <c:if test="${Cruise.id != idShip}">
                    <option value="${Cruise.id}">${Cruise.name}</option>
                </c:if>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td>Cruise status</td>
        <td><select name="idCruiseStatus">
            <c:if test="${idCruise == null}">
                <option disabled selected>Select cruise status</option>
            </c:if>
            <c:if test="${idCruise != null}">
                <option disabled>Select cruise status</option>
            </c:if>

            <c:forEach var="CruiseStatus" items="${CruiseStatuses}">
                <c:if test="${CruiseStatus.id == idCruiseStatus}">
                    <option selected value="${CruiseStatus.id}">${CruiseStatus.name}</option>
                </c:if>
                <c:if test="${CruiseStatus.id != idCruiseStatus}">
                    <option value="${CruiseStatus.id}">${CruiseStatus.name}</option>
                </c:if>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td>Ports</td>
        <td>
            <c:forEach var="Port" items="${Ports}">
                <c:if test="${fn:contains(selectedPorts, Port)}">
                    <input type="checkbox" checked name="selectedPorts" value="${Port.id}">${Port.name}<br/>
                </c:if>
                <c:if test="${!fn:contains(selectedPorts, Port)}">
                    <input type="checkbox" name="selectedPorts" value="${Port.id}">${Port.name}<br/>
                </c:if>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td></td>
        <td><c:if test="${idCruise == null}"><input type="submit" value="Add"></c:if>
            <c:if test="${idCruise != null}"><input type="submit" value="Update"></c:if></td>
    </tr>
    </form>
</table>
</body>
</html>
