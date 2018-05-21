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
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<%--<c:set var="page" value="/jsp/login.jsp" scope="session"/>--%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<p align="center">
<a href="CruiseCompany?command=changeLanguage&lang=ua"><button>Укр</button></a>
<a href="CruiseCompany?command=changeLanguage&lang=en"><button>Eng</button></a>
</p>
<form action="CruiseCompany" method="post">
    <input type="hidden" name="command" value="login"/>
    <table align="center">
        <tr>
            <td><fmt:message key="login"/></td>
            <td><input name="Login" value="${Login}"></td>
        </tr>
        <tr>
            <td><fmt:message key="password"/></td>
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
<p align="center"><ctg:customtag/></p>
</body>
</html>
