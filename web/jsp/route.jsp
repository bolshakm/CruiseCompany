<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 24.04.2018
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="routes"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="table-routes">
    <table border="1">
        <div class="header"><fmt:message key="routes"/></div>
        <c:if test="${user.role.id ==1}">
            <tr><a href="CruiseCompany?command=toRouteCard"><button><fmt:message key="add"/></button></a></tr>
        </c:if>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="cruises"/></th>
            <th><fmt:message key="ports"/></th>
            <c:if test="${user.role.id == 1}">
                <th><fmt:message key="actions"/></th>
            </c:if>
        </tr>
        <c:forEach var="Route" items="${Routes}" begin="${beginRoutes}" end="${endRoutes}">
            <tr>
                <td>${Route.name}</td>
                <td><c:forEach var="RouteHasPort" items="${Route.cruises}">
                    ${RouteHasPort.name}<br/>
                </c:forEach></td>
                <td><c:forEach var="RouteHasCruise" items="${Route.ports}">
                    ${RouteHasCruise.name}<br/>
                </c:forEach></td>
                <c:if test="${user.role.id == 1}">
                    <td>
                        <form action="CruiseCompany" method="post">
                            <input type="hidden" name="command" value="actionsForRoute"/>
                            <input type="hidden" name="idRoute" value="${Route.id}"/>
                            <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                            <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        <c:if test="${fn:length(pageNumbersRoutes)>1}">
            <tr align="right">
                <td colspan="10">
                    <c:forEach var="pageNumber" items="${pageNumbersRoutes}">
                        <a href="CruiseCompany?command=toRoutePage&pageNumberRoutes=${pageNumber}">${pageNumber}</a>
                    </c:forEach>
                </td>
            </tr>
        </c:if>
    </table>
</div>
</body>
</html>
