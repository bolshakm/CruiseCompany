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
    <%--<fmt:message key="login"/><br/>--%>
    Login<br/>
    <input name="Login" value="${Login}">
    <br/>
    <%--<fmt:message key="password"/><br/>--%>
    Password<br/>
    <input type="password" name="Password" value="${Password}">
    <br/>
    ${InfoMassage}
    <br/>
    <input type="submit" name="login" value="Login"/>
    <input type="submit" name="registration" value="Registration">
</form>
${massage}
</body>
</html>
