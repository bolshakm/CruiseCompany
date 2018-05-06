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
            <input type="hidden" name="command" value="updateUser"/>
            <input type="hidden" name="idUser" value="${idUser}">

        <td>Login</td>
        <td><c:if test="${user.login != login}">${login}</c:if>
            <c:if test="${user.login == login}"><input type="text" name="login" value="${login}"></c:if></td>
    </tr>
        <c:if test="${user.login == login}">
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" value="${password}"/></td>
            </tr>
            <tr>
                <td>Confirm password</td>
                <td><input type="password" name="passwordConfirm" value="${password}"/></td>
            </tr>
        </c:if>
        <tr>
            <td>Email</td>
            <td><c:if test="${user.login != login}">${email} </c:if>
                <c:if test="${user.login == login}"><input type="text" name="email" value="${email}"></c:if></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="${name}"/></td>
        </tr>
    <tr>
        <td>Last name</td>
        <td><input type="text" name="lastName" value="${lastName}"/></td>
    </tr>
        <c:if test="${user.role.id == 1}">
            <tr>
                <td>Money</td>
                <td><input type="number" name="money" value="${money}"></td>
            </tr>
        </c:if>
<c:if test="${user.role.id == 1}">
    <tr>
        <td>Role</td>
    <c:if test="${idRole != 2 && idRole != 1}">
        <td><select name="idRole">
            <c:forEach var="Role" items="${Roles}">
                <c:if test="${Role.id == idRole}">
                    <option selected value="${Role.id}">${Role.name}</option>
                </c:if>
                <c:if test="${Role.id != idRole}">
                    <option value="${Role.id}">${Role.name}</option>
                </c:if>
            </c:forEach>
        </select></td>
    </c:if>
        <c:if test="${idRole == 1}">
            <td>${user.role.name}</td>
        </c:if>
        <c:if test="${idRole == 2}">
        <td>User</td>
        </c:if>
    </tr>
    <c:if test="${idRole != 2 && idRole != 1}">
        <tr>
            <td>Ship</td>
            <td>
            <select name="ShipId">
                <c:forEach var="Ship" items="${Ships}">
                    <c:if test="${Ship.id == idShip}">
                        <option selected value="${Ship.id}">${Ship.name}</option>
                    </c:if>
                    <c:if test="${Ship.id != idShip}">
                        <option value="${Ship.id}">${Ship.name}</option>
                    </c:if>
                </c:forEach>
            </select></td></td>
        </tr>
    </c:if>
</c:if>
    <tr><td></td><td>
        <input type="submit" name="action" value="Update"></td></tr>
    </tr>
    </form>
</table>
<p align="center">${ErrorMassage}</p>
</body>
</html>
