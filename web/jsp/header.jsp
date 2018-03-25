<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />--%>
<%--<fmt:setLocale value="${language}" />--%>
<%--<fmt:setBundle basename="text" />--%>
<html>
<head>
    <title>Header</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/CruiseCompany" method="post" align = "center">
    <input type="hidden" name="command" value="redirect">
    <input type="submit" name="redirectTo" value="Cruises">
    <input type="submit" name="redirectTo" value="Ships">
    <input type="submit" name="redirectTo" value="Ports">
    <input type="submit" name="redirectTo" value="Users">
    <input type="submit" name="redirectTo" value="Tickets">
    <input type="submit" name="redirectTo" value="Logout">
</form>
</body>
</html>
