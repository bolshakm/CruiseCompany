<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1251" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Ports</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="table-port">
            <table border="1" align="center">
                <div class="header"><fmt:message key="excursions"/></div>
                <tr>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="port"/></th>
                    <th><fmt:message key="price"/></th>
                    <c:if test="${user.role.id == 1}">
                        <th>Actions</th>
                    </c:if>
                    <c:if test="${user.role.id == 1}">
                </tr>
                <c:if test="${Excursion == null || Excursion.id == 0}">
                    <form action="CruiseCompany" method="post">
                        <input type="hidden" name="command" value="addExcursion">
                        <tr>
                            <td><input type="text" name="name" value="${Excursion.name}"></td>
                            <td><select name="idPort">
                                <option disabled selected><fmt:message key="select.port"/></option>
                                <c:forEach var="Port" items="${Ports}">
                                    <c:if test="${Port.id == Excursion.port.id}">
                                        <option selected value="${Port.id}">${Port.name}</option>
                                    </c:if>
                                    <c:if test="${Port.id != Excursion.port.id}">
                                        <option value="${Port.id}">${Port.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select></td>
                            <td><input type="number" name="price" value="${Excursion.price}"></td>
                            <td><input type="submit" name="Add" value="<fmt:message key="add"/>"></td>
                        </tr>
                    </form>
                </c:if>
                <c:if test="${Excursion != null && Excursion.id != 0}">
                    <form action="CruiseCompany" method="post">
                        <input type="hidden" name="command" value="updateExcursion">
                        <input type="hidden" name="idExcursion" value="${Excursion.id}">
                        <tr>
                            <td><input type="text" name="name" value="${Excursion.name}"></td>
                            <td><select name="idPort">
                                <option disabled>Select Port</option>
                                <c:forEach var="Port" items="${Ports}">
                                    <c:if test="${Port.id == Excursion.port.id}">
                                        <option selected value="${Port.id}">${Port.name}</option>
                                    </c:if>
                                    <c:if test="${Port.id != Excursion.port.id}">
                                        <option value="${Port.id}">${Port.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select></td>
                            <td><input type="number" name="price" value="${Excursion.price}"></td>
                            <td><input type="submit" name="Update" value="<fmt:message key="update"/>"></td>
                        </tr>
                    </form>
                </c:if>
                </c:if>
                <c:forEach var="Excursion" items="${Excursions}" begin="${beginExcursions}" end="${endExcursions}">
                    <tr>
                        <td>${Excursion.name}</td>
                        <td>${Excursion.port.name}</td>
                        <td>${Excursion.price}</td>
                        <c:if test="${user.role.id == 1}">
                            <td>
                                <form action="CruiseCompany" method="post">
                                    <input type="hidden" name="command" value="actionsForExcursion"/>
                                    <input type="hidden" name="idExcursion" value="${Excursion.id}"/>
                                    <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                                    <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(pageNumbersExcursions)>1}">
                    <tr align="right">
                        <td colspan="10">
                            <c:forEach var="pageNumber" items="${pageNumbersExcursions}">
                                <a href="CruiseCompany?command=toPortsPage&pageNumberExcursions=${pageNumber}">${pageNumber}</a>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
            </table>
</div>
        <c:if test="${user.role.id == 1}">
<div class="table-excursion">
<table border="1" align="center">
                    <div class="header"><fmt:message key="ports"/></div>
                    <tr>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="city"/></th>
                        <th><fmt:message key="country"/></th>
                        <th><fmt:message key="actions"/></th>
                    </tr>
                    <c:if test="${Port == null || Port.id == 0}">
                        <tr>
                            <form action="CruiseCompany" method="post">
                                <td><input type="hidden" name="command" value="addPort">
                                    <input type="text" name="PortName" value="${Port.name}"></td>
                                <td><input type="text" name="CityName" value="${Port.city}"></td>
                                <td><input type="text" name="CountryName" value="${Port.country}"></td>
                                <td>
                                    <input type="submit" name="Add" value="<fmt:message key="add"/>">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:if test="${Port != null && Port.id != 0}">
                        <tr>
                            <form action="CruiseCompany" method="post">
                                <td>
                                    <input type="hidden" name="command" value="updatePort">
                                    <input type="hidden" name="idPort" value="${Port.id}">
                                    <input type="text" name="PortName" value="${Port.name}"></td>
                                <td><input type="text" name="CityName" value="${Port.city}"></td>
                                <td><input type="text" name="CountryName" value="${Port.country}"></td>
                                </td>
                                <td>
                                    <input type="submit" name="Update" value="<fmt:message key="update"/>">
                                </td>
                            </form>
                        </tr>
                    </c:if>
                    <c:forEach var="Port" items="${Ports}" begin="${beginPorts}" end="${endPorts}">
                        <tr>
                            <td>${Port.name}</td>
                            <td>${Port.city}</td>
                            <td>${Port.country}</td>
                            <td>
                                <form action="CruiseCompany" method="post">
                                    <input type="hidden" name="command" value="actionsForPort"/>
                                    <input type="hidden" name="idPort" value="${Port.id}"/>
                                    <input type="submit" name="actionUpdate" value="<fmt:message key="update"/>"/>
                                    <input type="submit" name="actionDelete" value="<fmt:message key="delete"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(pageNumbersPorts)>1}">
                        <tr align="right">
                            <td colspan="10">
                                <c:forEach var="pageNumber" items="${pageNumbersPorts}">
                                    <a href="CruiseCompany?command=toPortsPage&pageNumberPorts=${pageNumber}">${pageNumber}</a>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:if>
                </table>
        </c:if>
</div>
</body>
</html>
