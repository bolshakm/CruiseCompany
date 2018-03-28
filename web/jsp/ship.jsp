<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 01:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ships</title>
</head>
<body>
<p align="center">Hello ${user.name}</p>
<c:import url="header.jsp"/>
<table align="center">
    <tr>
        <td>
            <table border="1">
                <caption>Ships</caption>
                <tr>
                    <th>Name</th>
                    <th>Number</th>
                    <th>Number of seats</th>
                    <th>Price per seats</th>
                    <th>Ship type</th>
                    <th>Bonuses</th>
                    <th>Cruises</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="Ship" items="${Ships}">
                    <tr>
                        <td>${Ship.name}</td>
                        <td>${Ship.number}</td>
                        <td>${Ship.numberOfSeats}</td>
                        <td>${Ship.pricePerSeat}</td>
                        <td>${Ship.type.name}</td>
                        <td><c:forEach var="bonus" items="${Ship.bonuses}">
                            ${bonus.name}<br/>
                        </c:forEach></td>
                        <td><c:forEach var="cruise" items="${Ship.cruises}">
                            ${cruise.name}<br/>
                        </c:forEach></td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td width="100"></td>
        <td>
            <table border="1" bgcolor="#f0ffff">
                <caption>Ship Type</caption>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                <c:if test="${ShipTypeName == null}">
                <tr>
                    <form action="/CruiseCompany" method="post">
                        <td>
                        <input type="hidden" name="command" value="addShipType">
                        <input type="text" name="ShipTypeName">
                        </td>
                        <td>
                            <input type="submit" name="Add" value="Add">
                        </td>
                    </form>
                </tr>
                </c:if>
                <c:if test="${ShipTypeName != null}">
                    <tr>
                        <form action="/CruiseCompany" method="post">
                            <td>
                                <input type="hidden" name="command" value="updateShipType">
                                <input type="hidden" name="ShipTypeId" value="${ShipTypeId}">
                                <input type="text" name="ShipTypeName" value="${ShipTypeName}">
                            </td>
                            <td>
                                <input type="submit" name="Update" value="Update">
                            </td>
                        </form>
                    </tr>
                </c:if>
                <c:forEach var="ShipType" items="${ShipTypes}">
                    <tr>
                        <td>${ShipType.name}</td>
                        <td><a href="/CruiseCompany?command=updateShipType&ShipTypeId=${ShipType.id}"><button>Update</button></a>
                        <a href="/CruiseCompany?command=deleteShipType&ShipTypeId=${ShipType.id}"><button>Delete</button></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <table border="1" bgcolor="#deb887">
                <caption>Bonuses</caption>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                <c:if test="${BonusName == null}">
                    <tr>
                        <form action="/CruiseCompany" method="post">
                            <td>
                                <input type="hidden" name="command" value="addBonus">
                                <input type="text" name="BonusName">
                            </td>
                            <td>
                                <input type="submit" name="Add" value="Add">
                            </td>
                        </form>
                    </tr>
                </c:if>
                <c:if test="${BonusName != null}">
                    <tr>
                        <form action="/CruiseCompany" method="post">
                            <td>
                                <input type="hidden" name="command" value="updateBonus">
                                <input type="hidden" name="idBonus" value="${idBonus}">
                                <input type="text" name="BonusName" value="${BonusName}">
                            </td>
                            <td>
                                <input type="submit" name="Update" value="Update">
                            </td>
                        </form>
                    </tr>
                </c:if>
                <c:forEach var="Bonus" items="${Bonuses}">
                    <tr>
                        <td>${Bonus.name}</td>
                        <td><a href="/CruiseCompany?command=updateBonus&idBonus=${Bonus.id}"><button>Update</button></a>
                            <a href="/CruiseCompany?command=deleteBonus&idBonus=${Bonus.id}"><button>Delete</button></a> </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
