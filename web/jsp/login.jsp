<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 06.09.2017
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />--%>
<%--<fmt:setLocale value="${language}" />--%>
<%--<fmt:setBundle basename="text" />--%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
    <input type="hidden" name="command" value="login"/>
    <table align="center">
        <tr>
            <td>Login</td>
            <td><input name="Login" value="${Login}"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="Password" value="${Password}"></td>
        </tr>
        <tr>
            <td><input type="submit" name="button" value="Login"/></td>
            <td><input type="submit" name="button" value="Registration"></td>
        </tr>
    </table>
    <br/>
</form>
<p align="center"> ${ErrorMassage}</p>
</body>
</html>
