<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 31.03.2018
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="cruise.status"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption>Cruise Card</caption>
        <form action="CruiseCompany" method="post">
            <c:if test="${Cruise.id == null || Cruise.id ==0}"><input type="hidden" name="command" value="addCruise"/></c:if>
            <c:if test="${Cruise.id != null && Cruise.id != 0}"><input type="hidden" name="command" value="updateCruise"/></c:if>
            <tr>
            <input type="hidden" name="idCruise" value="${Cruise.id}">

            <td><fmt:message key="name"/></td>
            <td><input type="text" name="name" value="${Cruise.name}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="from"/></td>
        <td><input type="date" name="from" value="${Cruise.from}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="to"/></td>
        <td><input type="date" name="to" value="${Cruise.to}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="ship"/></td>
        <td>
            <select name="ShipId">
            <c:if test="${Cruise.id == null || Cruise.id == 0}">
                <option disabled selected><fmt:message key="select.ship"/></option>
            </c:if>
            <c:forEach var="Ship" items="${Ships}">
                <c:if test="${Ship.id == Cruise.ship.id}">
                    <option selected value="${Ship.id}">${Ship.name}</option>
                </c:if>
                <c:if test="${Ship.id != Cruise.ship.id}">
                    <option value="${Ship.id}">${Ship.name}</option>
                </c:if>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td><fmt:message key="cruise.status"/></td>
        <td><select name="CruiseStatusId">
            <c:if test="${Cruise.id == null || Cruise.id == 0}">
                <option disabled selected><fmt:message key="select.cruise.status"/></option>
            </c:if>
            <c:if test="${Cruise.id != null && Cruise.id != 0}">
                <option disabled>Select cruise status</option>
            </c:if>

            <c:forEach var="CruiseStatus" items="${CruiseStatuses}">
                <c:if test="${CruiseStatus.id == Cruise.status.id}">
                    <option selected value="${CruiseStatus.id}">${CruiseStatus.name}</option>
                </c:if>
                <c:if test="${CruiseStatus.id != Cruise.status.id}">
                    <option value="${CruiseStatus.id}">${CruiseStatus.name}</option>
                </c:if>
            </c:forEach>
        </select></td>
    </tr>
            <tr>
                <td><fmt:message key="route"/> </td>
                <td>
                    <select name="RouteId">
                        <c:if test="${Cruise.route.id == null}">
                            <option disabled selected><fmt:message key="select.route"/></option>
                        </c:if>
                        <c:forEach var="Route" items="${Routes}">
                            <c:if test="${Route.id == Cruise.route.id}">
                                <option selected value="${Route.id}">${Route.name}</option>
                            </c:if>
                            <c:if test="${Route.id != Cruise.route.id}">
                                <option value="${Route.id}">${Route.name}</option>
                            </c:if>
                        </c:forEach>
                    </select></td>
            </tr>
    <tr>
        <td></td>
        <td><c:if test="${Cruise.id == null || Cruise.id == 0}"><input type="submit" value="<fmt:message key="add"/>"></c:if>
            <c:if test="${Cruise.id != null && Cruise.id != 0}"><input type="submit" value="<fmt:message key="update"/>"></c:if></td>
    </tr>
    </form>
</table>
</body>
</html>
