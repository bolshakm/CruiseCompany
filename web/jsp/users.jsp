<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="users"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="table-users">
    <table border="1" bgcolor="#f0f8ff">
        <div class="header"><fmt:message key="users"/></div>
        <c:if test="${user.role.id ==1}">
            <tr><a href="CruiseCompany?command=toUserCard">
                <button><fmt:message key="add"/></button>
            </a></tr>
        </c:if>
        <tr>
            <th><fmt:message key="login"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="last.name"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="role"/></th>
            <th><fmt:message key="ship"/></th>
            <c:if test="${user.role.id == 1}">
                <th><fmt:message key="actions"/></th>
            </c:if>
        </tr>
        <c:forEach var="User" items="${Users}" begin="${beginUsers}" end="${endUsers}">
            <tr>
                <td>${User.login}</td>
                <td>${User.name}</td>
                <td>${User.lastName}</td>
                <td>${User.email}</td>
                <td>${User.role.name}</td>
                <td>${User.ship.name}</td>
                <c:if test="${user.role.id == 1}">
                    <td>
                        <form action="CruiseCompany" method="post">
                            <input type="hidden" name="command" value="actionsForUser"/>
                            <input type="hidden" name="idUser" value="${User.id}"/>
                            <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                            <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        <c:if test="${fn:length(pageNumbersUsers)>1}">
            <tr align="right">
                <td colspan="10">
                    <c:forEach var="pageNumber" items="${pageNumbersUsers}">
                        <a href="CruiseCompany?command=toUsersPage&pageNumberUsers=${pageNumber}">${pageNumber}</a>
                    </c:forEach>
                </td>
            </tr>
        </c:if>
    </table>
</div>
<c:if test="${user.role.id == 1}">
<div class="table-role">
    <table border="1" bgcolor="#f0ffff">
        <div class="header"><fmt:message key="roles"/></div>

        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="actions"/></th>
        </tr>
        <c:if test="${Role == null || Role.id == 0}">
            <tr>
                <form action="CruiseCompany" method="post">
                    <td>
                        <input type="hidden" name="command" value="addRole">
                        <input type="text" name="RoleName">
                    </td>
                    <td>
                        <input type="submit" name="Add" value="<fmt:message key="add"/>">
                    </td>
                </form>
            </tr>
        </c:if>
        <c:if test="${Role != null && Role.id != null}">
            <tr>
                <form action="CruiseCompany" method="post">
                    <td>
                        <input type="hidden" name="command" value="updateRole">
                        <input type="hidden" name="idRole" value="${Role.id}">
                        <input type="text" name="RoleName" value="${Role.name}">
                    </td>
                    <td>
                        <input type="submit" name="Update" value="<fmt:message key="update"/>">
                    </td>
                </form>
            </tr>
        </c:if>
        <c:forEach var="Role" items="${Roles}" begin="${beginRoles}" end="${endRoles}">
            <tr>
                <td>${Role.name}</td>
                <td>
                    <form action="CruiseCompany" method="post">
                        <input type="hidden" name="command" value="actionsForRole"/>
                        <input type="hidden" name="idRole" value="${Role.id}"/>
                        <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                        <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${fn:length(pageNumbersRoles)>1}">
            <tr align="right">
                <td colspan="10">
                    <c:forEach var="pageNumber" items="${pageNumbersRoles}">
                        <a href="CruiseCompany?command=toUsersPage&pageNumberRoles=${pageNumber}">${pageNumber}</a>
                    </c:forEach>
                </td>
            </tr>
        </c:if>
    </table>
    </td>
    </c:if>
</div>
</body>
</html>
