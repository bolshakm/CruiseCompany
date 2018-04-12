<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />--%>
<%--<fmt:setLocale value="${language}" />--%>
<%--<fmt:setBundle basename="text" />--%>
<html>
<head>
    <title>Header</title>
</head>
<body>
<p align="center">Welcome ${user.name}</p><br/>
<table align="right">
    <tr><td>Money:</td>
        <td>${user.money}</td><td width="100px  "></td></tr>
    <tr><td><a href="/CruiseCompany?command=toUpdateUserCard&idUser=${user.id}">${user.login}</a></td>
        <td><a href="CruiseCompany?command=logout">Logout</a></td></tr>
</table>

<p align="center">
    <c:if test="${user.role.id == 1}">
        <a href="/CruiseCompany?command=toCruisesPage">
            <button>Cruises</button>
        </a>
        <a href="/CruiseCompany?command=toShipsPage">
            <button>Ships</button>
        </a>
        <a href="/CruiseCompany?command=toPortsPage">
            <button>Ports</button>
        </a>
        <a href="/CruiseCompany?command=toUsersPage">
            <button>Users</button>
        </a>
        <a href="/CruiseCompany?command=toTicketsPage">
            <button>Tickets</button>
        </a>
    </c:if>
</p>
</body>
</html>
