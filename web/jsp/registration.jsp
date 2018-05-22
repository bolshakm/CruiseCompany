<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.09.2017
  Time: 01:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
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
<p align="center"><fmt:message key="glad.to.see.you"/></p><br/>
<p align="center">${ErrorMassage}</p>
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
        <tr><td><fmt:message key="login"/></td><td><input type="text" name="login" value="${User.login}"></td></tr>
        <tr><td><fmt:message key="password"/></td><td><input type="password" name="password" value="${User.password}"/></td></tr>
        <tr><td><fmt:message key="confirm.password"/></td><td><input type="password" name="passwordConfirm" value="${User.password}"/></td></tr>
        <tr><td><fmt:message key="name"/></td><td><input type="text" name="name" value="${User.name}"></td></tr>
        <tr><td><fmt:message key="last.name"/></td><td><input type="text" name="lastName" value="${User.lastName}"></td></tr>
        <tr><td><fmt:message key="email"/></td><td><input type="text" name="email" value="${User.email}"></td></tr>
        <c:if test="${user.role.id == 1}">
            <tr>
                <td><fmt:message key="role"/></td>
                <td><select name="idRole">
                    <option disabled selected><fmt:message key="select.role"/></option>
                    <c:forEach var="Role" items="${Roles}">
                            <option value="${Role.id}">${Role.name}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td><fmt:message key="ship"/></td>
                <td>
                    <select name="idShip">
                            <option disabled selected><fmt:message key="select.ship"/></option>
                        <c:forEach var="Ship" items="${Ships}">
                                <option value="${Ship.id}">${Ship.name}</option>
                        </c:forEach>
                    </select></td>
            </tr>
        </c:if>
        <tr><td></td><td>
            <c:if test="${user.role.id == 1}">
                <input type="submit" value="<fmt:message key="add"/>" >
            </c:if>
            <c:if test="${user == null}">
                <input type="submit" value="<fmt:message key="registration"/>" >
            </c:if></td></tr>
    </table>
</form>
</body>
</html>
