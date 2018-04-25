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
<form action="CruiseCompany" method="post">
    <input type="hidden" name="command" value="registration">
    <table align="center" bgcolor="yellow" border="1">
        <tr><td>Login</td><td><input type="text" name="login"></td></tr>
        <tr><td>Password</td><td><input type="password" name="password"></td></tr>
        <tr><td>Name</td><td><input type="text" name="name"></td></tr>
        <tr><td>Last name</td><td><input type="text" name="lastName"></td></tr>
        <tr><td>Email</td><td><input type="text" name="email"></td></tr>
        <tr><td></td><td><input type="submit" value="Registration" name="registration"></td></tr>
    </table>

</form>
</body>
</html>
