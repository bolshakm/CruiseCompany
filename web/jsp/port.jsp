<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <caption>Excursions</caption>
                <tr>
                    <th>Name</th>
                    <th>Port</th>
                    <th>Price</th>
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
                                <option disabled selected>Select Port</option>
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
                            <td><input type="submit" name="Add" value="Add"></td>
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
                            <td><input type="submit" name="Update" value="Update"></td>
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
                                <a href="CruiseCompany?command=updateExcursion&idExcursion=${Excursion.id}"><button>Update</button></a>
                                <a href="CruiseCompany?command=deleteExcursion&idExcursion=${Excursion.id}"><button>Delete</button></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <c:if test="${user.role.id == 1}">
            <td width="50px"></td>
            <td valign="top">
                <table border="1" align="center">
                    <caption>Ports</caption>
                    <tr>
                        <th>Name</th>
                        <th>City</th>
                        <th>Country</th>
                        <th>Actions</th>
                    </tr>
                    <c:if test="${Port == null || Port.id == 0}">
                        <tr>
                            <form action="CruiseCompany" method="post">
                                <td><input type="hidden" name="command" value="addPort">
                                    <input type="text" name="PortName" value="${Port.name}"></td>
                                <td><input type="text" name="CityName" value="${Port.city}"></td>
                                <td><input type="text" name="CountryName" value="${Port.country}"></td>
                                <td>
                                    <input type="submit" name="Add" value="Add">
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
                                    <input type="submit" name="Update" value="Update">
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
                                <a href="CruiseCompany?command=updatePort&idPort=${Port.id}"><button>Update</button></a>
                                <a href="CruiseCompany?command=deletePort&idPort=${Port.id}"><button>Delete</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </c:if>
    </tr>
</table>
</body>
</html>
