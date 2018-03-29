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
<p align="center">Hello ${user.name}</p>
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
                    <th>Actions</th>
                </tr>
                <c:forEach var="Excursion" items="${Excursions}">
                    <tr>
                        <td>${Excursion.name}</td>
                        <td>${Excursion.port.name}</td>
                        <td>${Excursion.price}</td>
                        <td><a href="/CruiseCompany?command=updateExcursion&idExcursion=${Excursion.id}">
                            <button>Update</button>
                        </a>
                            <a href="/CruiseCompany?command=deleteExcursion&idExcursion=${Excursion.id}">
                                <button>Delete</button>
                            </a></td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td valign="top">
            <table border="1" align="center">
                <caption>Ports</caption>
                <tr>
                    <th>Name</th>
                    <th>City</th>
                    <th>Country</th>
                    <th>Actions</th>
                </tr>
                <c:if test="${PortName == null}">
                    <tr>
                        <form action="/CruiseCompany" method="post">
                            <td>
                                <input type="hidden" name="command" value="addPort">
                            <input type="text" name="PortName"></td>
                            <td><input type="text" name="CityName"></td>
                            <td><input type="text" name="CountryName"></td>
                            </td>
                            <td>
                                <input type="submit" name="Add" value="Add">
                            </td>
                        </form>
                    </tr>
                </c:if>
                <c:if test="${PortName != null}">
                    <tr>
                        <form action="/CruiseCompany" method="post">
                            <td>
                                <input type="hidden" name="command" value="updatePort">
                                <input type="hidden" name="idPort" value="${idPort}">
                            <input type="text" name="PortName" value="${PortName}"></td>
                            <td><input type="text" name="CityName" value="${CityName}"></td>
                            <td><input type="text" name="CountryName" value="${CountryName}"></td>
                                <%--<input type="text" name="PortName" value="${Port.name}"></td>--%>
                            <%--<td><input type="text" name="CityName" value="${Port.city}"></td>--%>
                            <%--<td><input type="text" name="CountryName" value="${Port.country}"></td>--%>
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
                        <td><a href="/CruiseCompany?command=updatePort&idPort=${Port.id}"><button>Update</button></a>
                            <a href="/CruiseCompany?command=deletePort&idPort=${Port.id}"><button>Delete</button></a></td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>

</body>
</html>
