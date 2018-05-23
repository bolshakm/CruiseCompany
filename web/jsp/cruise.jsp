<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="cruises"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<table border="1" align="center">
    <tr>
        <th><fmt:message key="name"/></th>
        <th><fmt:message key="from"/></th>
        <th><fmt:message key="to"/></th>
        <th><fmt:message key="ship.number"/></th>
        <th><fmt:message key="cruise.status"/></th>
        <th><fmt:message key="route"/></th>
        <th><fmt:message key="actions"/></th>
    </tr>
    <form action="CruiseCompany" method="post">
        <input type="hidden" name="command" value="searchCruise">
        <tr>
            <td><input type="text" name="name"></td>
            <td><input type="date" name="from"></td>
            <td><input type="date" name="to"></td>
            <td><input type="text" name="shipNumber"></td>
            <td><select name="cruiseStatusId">
                <option disabled selected><fmt:message key="select.cruise.status"/></option>
                <c:forEach var="CruiseStatus" items="${CruiseStatuses}">
                    <option value="${CruiseStatus.id}">${CruiseStatus.name}</option>
                </c:forEach>
            </select></td>
            <td><select name="routesId">
                <option disabled selected><fmt:message key="select.route"/></option>
                <c:forEach var="Route" items="${Routes}">
                    <option value="${Route.id}">${Route.name}</option>
                </c:forEach>
            </select></td>
            <td><input type="submit" value="<fmt:message key="search"/>"></td>
        </tr>
    </form>
</table>
<table align="center">
    <tr>
        <td valign="top">
            <table border="1" bgcolor="#7fffd4">
                <caption><fmt:message key="cruises"/></caption>
                <c:if test="${user.role.id == 1}">
                    <tr align="right">
                        <td colspan="10"><a href="CruiseCompany?command=toCruiseCard">
                            <button><fmt:message key="add"/></button>
                        </a>
                    </tr>
                    </td>
                </c:if>
                <tr>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="from"/></th>
                    <th><fmt:message key="to"/></th>
                    <th><fmt:message key="ship.number"/></th>
                    <th><fmt:message key="cruise.status"/></th>
                    <th><fmt:message key="ticket.seats"/></th>
                    <th><fmt:message key="route"/></th>
                    <th><fmt:message key="actions"/></th>
                </tr>
                <c:forEach var="Cruise" items="${Cruises}" begin="${beginCruises}" end="${endCruises}">
                    <tr>
                        <td>${Cruise.name}</td>
                        <td>${Cruise.from}</td>
                        <td>${Cruise.to}</td>
                        <td>${Cruise.ship.number}</td>
                        <td>${Cruise.status.name}</td>
                        <td>${fn:length(Cruise.tickets)}/${Cruise.ship.numberOfSeats}</td>
                        <td>${Cruise.route.name}</td>
                        <c:if test="${user.role.id == 1}">
                            <td>
                                <form action="CruiseCompany" method="post">
                                    <input type="hidden" name="command" value="actionsForCruise"/>
                                    <input type="hidden" name="idCruise" value="${Cruise.id}"/>
                                    <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                                    <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                                </form>
                            </td>
                        </c:if>
                        <c:if test="${user.role.id == 2}">
                            <td><a href="/CruiseCompany?command=buyTicket&idCruise=${Cruise.id}">
                                <button><fmt:message key="buy"/></button>
                            </a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(pageNumbersCruises)>1}">
                    <tr align="right">
                        <td colspan="10">
                            <c:forEach var="pageNumber" items="${pageNumbersCruises}">
                                <a href="CruiseCompany?command=toCruisePage&pageNumberCruises=${pageNumber}">${pageNumber}</a>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
            </table>
        </td>
        <td width="10"></td>
        <td valign="top">
            <c:if test="${user.role.id == 1}">
                <table border="1" bgcolor="#f0ffff">
                    <caption><fmt:message key="cruise.status"/></caption>
                    <tr>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="actions"/></th>
                    </tr>
                    <tr>
                        <c:if test="${SelectedCruiseStatus == null}">
                            <form action="CruiseCompany" method="post">
                                <input type="hidden" name="command" value="addCruiseStatus">
                                <td><input type="text" name="CruiseStatusName"></td>
                                <td><input type="submit" name="action" value="<fmt:message key="add"/>">
                            </form>
                        </c:if>
                        <c:if test="${SelectedCruiseStatus != null}">
                            <form action="CruiseCompany" method="post">
                                <input type="hidden" name="command" value="updateCruiseStatus">
                                <input type="hidden" name="cruiseStatusId" value="${SelectedCruiseStatus.id}">
                                <td><input type="text" name="CruiseStatusName" value="${SelectedCruiseStatus.name}">
                                </td>
                                <td><input type="submit" name="action" value="<fmt:message key="update"/>">
                            </form>
                        </c:if>
                        </form>
                    </tr>
                    <c:forEach var="CruiseStatus" items="${CruiseStatuses}" begin="${beginCruiseStatuses}"
                               end="${endCruiseStatuses}">
                        <tr>
                            <td>${CruiseStatus.name}</td>
                            <td>
                                <form action="CruiseCompany" method="post">
                                    <input type="hidden" name="command" value="actionsForCruiseStatus"/>
                                    <input type="hidden" name="cruiseStatusId" value="${CruiseStatus.id}"/>
                                    <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                                    <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(pageNumbersCruiseStatuses)>1}">
                        <tr align="right">
                            <td colspan="2">
                                <c:forEach var="pageNumber" items="${pageNumbersCruiseStatuses}">
                                <a href="CruiseCompany?command=toCruisePage&pageNumberCruiseStatuses=${pageNumber}">${pageNumber}</a>
                                </c:forEach>
                        </tr>
                    </c:if>
                </table>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>
