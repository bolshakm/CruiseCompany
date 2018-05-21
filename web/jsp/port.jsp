<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Ports</title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center">
    <tr>
        <td valign="top">
            <table border="1" align="center">
                <caption><fmt:message key="excursions"/></caption>
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
                <c:forEach var="Excursion" items="${Excursions}">
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
            </table>
        </td>
        <c:if test="${user.role.id == 1}">
            <td width="50px"></td>
            <td valign="top">
                <table border="1" align="center">
                    <caption><fmt:message key="ports"/></caption>
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
                    <c:forEach var="Port" items="${Ports}">
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
                </table>
            </td>
        </c:if>
    </tr>
</table>
</body>
</html>
