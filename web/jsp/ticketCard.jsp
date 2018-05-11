<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 03.04.2018
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket Card</title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <c:if test="${idTicket != null}">
        <caption>Ticket #${idTicket}</caption>
    </c:if>
    <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
        <c:if test="${idTicket != null}">
            <input type="hidden" name="command" value="updateTicket"/>
        </c:if>
        <c:if test="${idTicket == null}">
            <c:if test="${price == null}">
                <input type="hidden" name="command" value="getPrice"/>
            </c:if>
            <c:if test="${price != null}">
                <input type="hidden" name="command" value="addTicket"/>
            </c:if>
        </c:if>
        <tr>
            <input type="hidden" name="idTicket" value="${idTicket}">
            <td>Login</td>
            <td>${login}
                <input type="hidden" name="login" value="${login}">
            </td>
        </tr>
        <tr>
            <td>Name</td>
            <td>
                <c:if test="${price == null}">
                    <input type="text" name="name" value="${name}"/>
                </c:if>
                <c:if test="${price != null}">
                    ${name}
                    <input type="hidden" name="name" value="${name}">
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Last name</td>
            <td>
                <c:if test="${price == null}">
                    <input type="text" name="lastName" value="${lastName}"/>
                </c:if>
                <c:if test="${price != null}">
                    ${lastName}
                    <input type="hidden" name="lastName" value="${lastName}">
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Cruise</td>
            <c:if test="${user.role.id == 1}">
                <td>
                    <select name="CruiseId">
                        <c:forEach var="Cruise" items="${Cruises}">
                            <c:if test="${Cruise.id == idCruise}">
                                <option selected value="${Cruise.id}">${Cruise.name}</option>
                            </c:if>
                            <c:if test="${Cruise.id != idCruise}">
                                <option value="${Cruise.id}">${Cruise.name}</option>
                            </c:if>
                        </c:forEach>
                    </select></td>
            </c:if>
            <c:if test="${user.role.id != 1}">
                <td>${cruise.name}</td>
                <input type="hidden" name="idCruise" value="${cruise.id}">
            </c:if>
        </tr>
        <c:if test="${user.role.id != 1}">
        <tr>
            <td>Cruise from</td>
                <td>${cruise.from}</td>
        </tr>
        </c:if>
        <c:if test="${user.role.id != 1}">
            <tr>
                <td>Cruise to</td>
                <td>${cruise.to}</td>
            </tr>
        </c:if>
        <tr>
            <td>Ticket type</td>
            <td>
                <c:if test="${price == null}">
                    <select name="TicketTypeId">
                        <c:forEach var="TicketType" items="${TicketTypes}">
                            <c:if test="${TicketType.id == idTicketType}">
                                <option selected value="${TicketType.id}">${TicketType.name}</option>
                            </c:if>
                            <c:if test="${TicketType.id != idTicketType}">
                                <option value="${TicketType.id}">${TicketType.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${price != null}">
                    ${selectedTicketType.name}
                    <input type="hidden" name="selectedTicketTypeId" value="${selectedTicketType.id}">
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Excursions</td>
            <td>
                <c:if test="${price == null}">
                    <c:forEach var="Excursion" items="${Excursions}">
                        <c:if test="${fn:contains(selectedExcursions, Excursion)}">
                    <input type="checkbox" checked name="selectedExcursions"
                           value="${Excursion.id}">${Excursion.name}<br/>
                </c:if>
                <c:if test="${!fn:contains(selectedExcursions, Excursion)}">
                    <input type="checkbox" name="selectedExcursions" value="${Excursion.id}">${Excursion.name}
                    <br/>
                </c:if>
                </c:forEach>
                </c:if>
                <c:if test="${price != null}">
                    <c:forEach var="Excursion" items="${Excursions}">
                        <input type="hidden" name="selectedExcursions" value="${Excursion.id}">${Excursion.name}<br/>
                    </c:forEach>

                </c:if>
            </td>
        </tr>
        <c:if test="${!empty Bonuses}">
        <tr>
            <td>Bonuses</td>
            <td><c:forEach var="Bonus" items="${Bonuses}">
                ${Bonus.name}<br/>
            </c:forEach>
            </td>
        </tr>
        </c:if>
        <c:if test="${price != null}">

        <tr>
            <td>Price</td>
            <td>${price}</td>
        </tr>
        </c:if>
        <tr>
            <td></td>
            <td>
                <c:if test="${idTicket != null}">
                    <input type="submit" value="Update">
                </c:if>
                <c:if test="${idTicket == null}">
                    <c:if test="${price == null}">
                        <input type="submit" value="Get Price">
                    </c:if>
                    <c:if test="${price != null}">
                        <input type="submit" value="Buy">
                    </c:if>
                </c:if></td>
        </tr>
    </form>
</table>
</body>
</html>
