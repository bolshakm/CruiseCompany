<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="cruise.card"/></title>
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
<c:import url="header.jsp"/>
<div class="table-cruises-card">
<table align="center" border="1px ">
    <div class="header"><fmt:message key="cruise.card"/></div>

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
            <c:if test="${Cruise.id != null && Cruise.id != 0}"><input type="submit" name="actionUpdate" value="<fmt:message key="update"/>">
                <input type="submit" name="actionDelete" value="<fmt:message key="new"/>"></c:if></td>
    </tr>
    </form>
</table>
</div>
</body>
</html>
