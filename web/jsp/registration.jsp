<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.09.2017
  Time: 01:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Registration</title>
</head>
<body>
<c:if test="${user != null}">
    <c:import url="header.jsp"/>
</c:if>
<form action="CruiseCompany" method="post">
    <c:if test="${user == null}">
        <input type="hidden" name="command" value="registration">
    </c:if>
    <c:if test="${user.role.id == 1}">
        <input type="hidden" name="command" value="addUser">
    </c:if>
    <table align="center" bgcolor="yellow" border="1">
        <tr><td>Login</td><td><input type="text" name="login"></td></tr>
        <tr><td>Password</td><td><input type="password" name="password"></td></tr>
        <tr><td>Name</td><td><input type="text" name="name"></td></tr>
        <tr><td>Last name</td><td><input type="text" name="lastName"></td></tr>
        <tr><td>Email</td><td><input type="text" name="email"></td></tr>
        <c:if test="${user.role.id == 1}">
            <tr>
                <td>Role</td>
                <td><select name="idRole">
                    <option disabled selected>Select role</option>
                    <c:forEach var="Role" items="${Roles}">
                            <option value="${Role.id}">${Role.name}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td>Ship </td>
                <td>
                    <select name="idShip">
                            <option disabled selected>Select ship</option>
                        <c:forEach var="Ship" items="${Ships}">
                                <option value="${Ship.id}">${Ship.name}</option>
                        </c:forEach>
                    </select></td>
            </tr>
        </c:if>
        <tr><td></td><td>
            <c:if test="${user.role.id == 1}">
                <input type="submit" value="Add" >
            </c:if>
            <c:if test="${user == null}">
                <input type="submit" value="Registration" >
            </c:if></td></tr>
    </table>

</form>
</body>
</html>
