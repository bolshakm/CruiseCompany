<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<c:set var="Login" value="user2" scope="request"/>
<c:set var="Password" value="1234" scope="request"/>
<%--delete after testing--%>
<c:set var="adminLogin" value="admin" scope="request"/>
<jsp:forward page="jsp/login.jsp"/>
</body>
</html>
