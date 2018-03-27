<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 20.09.2017
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<%--For what?--%>
<%--<c:set var="page" value="administrator"/>--%>
<html>
<head>
    <title>Administrator</title>
</head>
<body>
<br/>
<c:import url="cruise.jsp"/>
<%--<c:choose>--%>
    <%--<c:when test="${page == 'ship'}">--%>
        <%--<c:import url="ship.jsp"/>--%>
    <%--</c:when>--%>
    <%--<c:when test="${page == 'cruise'}">--%>
        <%--<c:import url="cruise.jsp"/>--%>
    <%--</c:when>--%>
    <%--<c:when test="${page == 'port'}">--%>
        <%--<c:import url="port.jsp"/>--%>
    <%--</c:when>--%>
    <%--<c:when test="${page == 'users'}">--%>
        <%--<c:import url="users.jsp"/>--%>
    <%--</c:when>--%>
    <%--<c:when test="${page == 'tickets'}">--%>
        <%--<c:import url="tickets.jsp"/>--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
        <%--<c:import url="cruise.jsp"/>--%>
    <%--</c:otherwise>--%>
<%--</c:choose>--%>

<%--<table border="1">--%>
    <%--<caption><fmt:message key="entrants"/></caption>--%>

    <%--<tr>--%>
        <%--<th><a href="SelectionCommittee?command=sortEntrantByName"><fmt:message key="name"/></a></th>--%>
        <%--<th><a href="SelectionCommittee?command=sortEntrantBySecondName"><fmt:message key="second.name"/></a></th>--%>
        <%--<th><fmt:message key="faculties"/></th>--%>
        <%--<th><fmt:message key="subject.with.marks"/></th>--%>
    <%--</tr>--%>
    <%--<c:forEach var="Entrant" items="${Entrants}">--%>
        <%--<tr>--%>
            <%--<td>${Entrant.user.name}</td>--%>
            <%--<td>${Entrant.user.lastName}</td>--%>
            <%--<td><c:forEach var="Faculty" items="${Entrant.faculties}">${Faculty.name}<br/></c:forEach> </td>--%>
            <%--<td><c:forEach var="Subject" items="${Entrant.subjectsWithMarks}">${Subject.key} - ${Subject.value}<br/></c:forEach> </td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
<%--</table>--%>
<%--<c:forEach var="page" items="${pages}">--%>
    <%--<a href="SelectionCommittee?command=toAdministrator&page=${page}">${page} </a>--%>
<%--</c:forEach>--%>
</body>
</html>
