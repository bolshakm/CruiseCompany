<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="statistic" uri="statistic" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 24.05.2018
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistic</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="table-statistic">
<statistic:table-statistic rows="${CruiseComesMoney.size}" head="Cruise comes money">
    ${CruiseComesMoney.statistic}
</statistic:table-statistic>
</div>
</body>
</html>
