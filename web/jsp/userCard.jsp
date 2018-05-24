<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 05.04.2018
  Time: 00:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="user.card"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="table-users-card">
    <table align="center" border="1px ">
        <div class="header"><fmt:message key="user.card"/></div>
        <form action="CruiseCompany" method="post">
    <tr>
        <c:if test="${User == null || User.id == 0}">
            <input type="hidden" name="command" value="addUser"/>
        </c:if>
        <c:if test="${User != null && User.id != 0}">
            <input type="hidden" name="command" value="updateUser"/>
        </c:if>
            <input type="hidden" name="idUser" value="${User.id}">

        <td><fmt:message key="login"/></td>
        <td><c:if test="${user.id != User.id && User.id != 0}">${User.login}<input type="hidden" name="login" value="${User.login}"></c:if>
            <c:if test="${user.id == User.id || User.id == null || User.id == 0}"><input type="text" name="login" value="${User.login}"></c:if></td>
    </tr>
        <c:if test="${user.id == User.id || User.id == null || User.id == 0}">
            <tr>
                <td><fmt:message key="password"/></td>
                <td><input type="password" name="password" value="${User.password}"/></td>
            </tr>
            <tr>
                <td><fmt:message key="confirm.password"/></td>
                <td><input type="password" name="passwordConfirm" value="${User.password}"/></td>
            </tr>
        </c:if>
        <tr>
            <td><fmt:message key="email"/></td>
            <td><c:if test="${user.id != User.id && User.id != 0}">${User.email}<input type="hidden" name="email" value="${User.email}"></c:if>
                <c:if test="${user.id == User.id  || User.id == null || User.id == 0}"><input type="text" name="email" value="${User.email}"></c:if></td>
        </tr>
        <tr>
            <td><fmt:message key="name"/></td>
            <td><input type="text" name="name" value="${User.name}"/></td>
        </tr>
    <tr>
        <td><fmt:message key="last.name"/></td>
        <td><input type="text" name="lastName" value="${User.lastName}"/></td>
    </tr>
        <c:if test="${user.role.id == 1 && user.role.id != User.id && User != null && User.id != 0}">
            <tr>
                <td><fmt:message key="money"/></td>
                <td><input type="text" name="money" value="${User.money}"></td>
            </tr>
        </c:if>
<c:if test="${user.role.id == 1}">
    <tr>
        <td><fmt:message key="role"/></td>
    <c:if test="${User.role.id != 2 && User.role.id != 1}">
        <td><select name="idRole">
            <option disabled selected><fmt:message key="select.role"/></option>
            <c:forEach var="Role" items="${Roles}">
                <c:if test="${Role.id == User.role.id}">
                    <option selected value="${Role.id}">${Role.name}</option>
                </c:if>
                <c:if test="${Role.id != User.role.id}">
                    <option value="${Role.id}">${Role.name}</option>
                </c:if>
            </c:forEach>
        </select></td>
    </c:if>
        <c:if test="${User.role.id == 1}">
            <td>${user.role.name}</td>
        </c:if>
        <c:if test="${User.role.id == 2}">
        <td>User</td>
        </c:if>
    </tr>
    <c:if test="${User.role.id != 2 && User.role.id != 1}">
        <tr>
            <td><fmt:message key="ship"/></td>
            <td>
            <select name="idShip">
                <option disabled selected><fmt:message key="select.ship"/></option>
                <c:forEach var="Ship" items="${Ships}">
                    <c:if test="${Ship.id == User.ship.id}">
                        <option selected value="${Ship.id}">${Ship.name}</option>
                    </c:if>
                    <c:if test="${Ship.id != User.ship.id}">
                        <option value="${Ship.id}">${Ship.name}</option>
                    </c:if>
                </c:forEach>
            </select></td></td>
        </tr>
    </c:if>
</c:if>
    <tr><td></td><td>
        <c:if test="${User == null || User.id == 0}">
            <input type="submit" name="action" value="<fmt:message key="add"/>">
        </c:if>
        <c:if test="${User != null && User.id != 0}">
            <input type="submit" name="action" value="<fmt:message key="update"/>">
        </c:if>
    </tr>
    </form>
</table>
    </div>
</body>
</html>
