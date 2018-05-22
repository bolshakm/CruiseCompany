<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
</head>
<body>
<c:import url="header.jsp"/>
<table align="center"><tr><td>
    <table border="1"  bgcolor="yellow">
        <caption><fmt:message key="routes"/></caption>
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
        <c:forEach var="Route" items="${Routes}">
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
    </table></td></tr>
</table>
</body>
</html>
