<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />--%>
<%--<fmt:setLocale value="${language}" />--%>
<%--<fmt:setBundle basename="text" />--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Header</title>--%>
<%--</head>--%>
<%--<body>--%>
<p align="center">Welcome ${user.name}</p><br/>
<table align="right">
    <tr><td>Money:</td>
        <td>${user.money}</td><td width="100px"></td></tr>
    <tr><td><a href="${pageContext.request.contextPath}/CruiseCompany?command=toUpdateUserCard&idUser=${user.id}">${user.login}</a></td>
        <td><a href="CruiseCompany?command=logout">logout</a></td></tr>
</table>
<p align="center">
    <c:if test="${user.role.id == 1 || user.role.id == 2}">
    <c:if test="${user.role.id == 1}">
        <a href="${pageContext.request.contextPath}/CruiseCompany?command=toCruisesPage"><button>Cruises</button></a>
    </c:if>
    <c:if test="${user.role.id != 1}">
        <a href="${pageContext.request.contextPath}/CruiseCompany?command=toMainPage"><button>Cruises</button></a>
    </c:if>
        <a href="${pageContext.request.contextPath}/CruiseCompany?command=toShipsPage"><button>Ships</button></a>
    <c:if test="${user.role.id == 1}">
        <a href="${pageContext.request.contextPath}/CruiseCompany?command=toPortsPage"><button>Ports</button></a>
    </c:if>
            <a href="${pageContext.request.contextPath}/CruiseCompany?command=toRoutePage"><button>Routes</button></a>
    <c:if test="${user.role.id != 1}">
        <a href="${pageContext.request.contextPath}/CruiseCompany?command=toPortsPage"><button>Excursions</button></a>
    </c:if>
    <c:if test="${user.role.id == 1}">
        <a href="${pageContext.request.contextPath}/CruiseCompany?command=toUsersPage"><button>Users</button></a>
    </c:if>
        <a href="${pageContext.request.contextPath}/CruiseCompany?command=toTicketsPage"><button>Tickets</button></a>
    </c:if>
</p>
<%--</body>--%>
<%--</html>--%>
