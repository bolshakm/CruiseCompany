<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<p align="center"> <a href="/CruiseCompany?command=toUpdateUserCard&idUser=${user.id}">${user.login}</a>| <a href="CruiseCompany?command=logout">Logout</a></p><br/>
<p align="center">
<a href="/CruiseCompany?command=toCruisesPage"><button>Cruises</button></a>
<a href="/CruiseCompany?command=toShipsPage"><button>Ships</button></a>
<a href="/CruiseCompany?command=toPortsPage"><button>Ports</button></a>
<a href="/CruiseCompany?command=toUsersPage"><button>Users</button></a>
<a href="/CruiseCompany?command=toTicketsPage"><button>Tickets</button></a>
<%--<a href="/CruiseCompany?command=logout"><button>Logout</button></a>--%>
</p>
</body>
</html>
