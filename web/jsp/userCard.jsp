<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 05.04.2018
  Time: 00:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User card</title>
</head>
<body>
    <c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption>User Card</caption>
    <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
    <tr>
        <c:if test="${User == null || User.id == 0}">
            <input type="hidden" name="command" value="addUser"/>
        </c:if>
        <c:if test="${User != null && User.id != 0}">
            <input type="hidden" name="command" value="updateUser"/>
        </c:if>
            <input type="hidden" name="idUser" value="${User.id}">

        <td>Login</td>
        <td><c:if test="${user.id != User.id && User.id != 0}">${User.login}<input type="hidden" name="login" value="${User.login}"></c:if>
            <c:if test="${user.id == User.id || User.id == null || User.id == 0}"><input type="text" name="login" value="${User.login}"></c:if></td>
    </tr>
        <c:if test="${user.id == User.id || User.id == null || User.id == 0}">
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" value="${User.password}"/></td>
            </tr>
            <tr>
                <td>Confirm password</td>
                <td><input type="password" name="passwordConfirm" value="${User.password}"/></td>
            </tr>
        </c:if>
        <tr>
            <td>Email</td>
            <td><c:if test="${user.id != User.id && User.id != 0}">${User.email}<input type="hidden" name="email" value="${User.email}"></c:if>
                <c:if test="${user.id == User.id  || User.id == null || User.id == 0}"><input type="text" name="email" value="${User.email}"></c:if></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="${User.name}"/></td>
        </tr>
    <tr>
        <td>Last name</td>
        <td><input type="text" name="lastName" value="${User.lastName}"/></td>
    </tr>
        <c:if test="${user.role.id == 1 && user.role.id != User.id && User != null && User.id != 0}">
            <tr>
                <td>Money</td>
                <td><input type="text" name="money" value="${User.money}"></td>
            </tr>
        </c:if>
<c:if test="${user.role.id == 1}">
    <tr>
        <td>Role</td>
    <c:if test="${User.role.id != 2 && User.role.id != 1}">
        <td><select name="idRole">
            <option disabled selected>Select role</option>
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
            <td>Ship</td>
            <td>
            <select name="idShip">
                <option disabled selected>Select ship</option>
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
            <input type="submit" name="action" value="Add">
        </c:if>
        <c:if test="${User != null && User.id != 0}">
            <input type="submit" name="action" value="Update">
        </c:if>
    </tr>
    </form>
</table>
</body>
</html>
