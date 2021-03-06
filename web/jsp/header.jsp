<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="myteg" uri="titletag" %>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<p align="center"><myteg:title><fmt:message key="welcome"/>, ${user.name}</myteg:title></p>
<div class="table-money">
<table border="1">
    <tr><td><fmt:message key="money"/>:</td>
        <td>${user.money}</td></tr>
    <tr><td><a href="CruiseCompany?command=toUpdateUserCard&idUser=${user.id}">${user.login}</a></td>
        <td><a href="CruiseCompany?command=logout"><fmt:message key="logout"/></a></td></tr>
</table>
</div>
<p align="center">
    <c:if test="${user.role.id ==1 || user.role.id == 2 || user.role.id == 3}">
        <a href="CruiseCompany?command=toMainPage"><button><fmt:message key="cruises"/></button></a>
        <a href="CruiseCompany?command=toShipsPage"><button><fmt:message key="ships"/></button></a>
        <c:if test="${user.role.id ==  1|| user.role.id ==2}">
    <c:if test="${user.role.id == 1}">
        <a href="CruiseCompany?command=toPortsPage"><button><fmt:message key="ports"/></button></a>
    </c:if>
            <a href="CruiseCompany?command=toRoutePage"><button><fmt:message key="routes"/></button></a>
    <c:if test="${user.role.id != 1}">
        <a href="CruiseCompany?command=toPortsPage"><button><fmt:message key="excursions"/></button></a>
    </c:if>
        </c:if>
    <c:if test="${user.role.id == 1}">
        <a href="CruiseCompany?command=toUsersPage"><button><fmt:message key="users"/></button></a>
    </c:if>
        <c:if test="${user.role.id == 3}">
            <a href="CruiseCompany?command=toUsersPage"><button><fmt:message key="stuff"/></button></a>
        </c:if>
        <a href="CruiseCompany?command=toTicketsPage"><button><fmt:message key="tickets"/></button></a>
    </c:if>
    <c:if test="${user.role.id == 1}">
        <a href="CruiseCompany?command=toStatisticPage"><button><fmt:message key="statistic"/></button></a>
    </c:if>
</p>
<br/>
<p align="center">${ErrorMassage}</p>
