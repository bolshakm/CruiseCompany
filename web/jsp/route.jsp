<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 24.04.2018
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route</title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center"><tr><td>
    <table border="1"  bgcolor="yellow">
        <caption>Routes</caption>
        <c:if test="${user.role.id ==1}">
            <tr><a href="CruiseCompany?command=toRouteCard"><button>Add</button></a></tr>
        </c:if>
        <tr>
            <th>Name</th>
            <th>Cruises</th>
            <th>Ports</th>
            <c:if test="${user.role.id == 1}">
                <th>Actions</th>
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
                        <a href="CruiseCompany?command=toUpdateRoute&idRoute=${Route.id}"><button>Update</button></a>
                        <a href="$CruiseCompany?command=deleteRoute&idRoute=${Route.id}"><button>Delete</button></a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table></td></tr>
</table>
</body>
</html>
