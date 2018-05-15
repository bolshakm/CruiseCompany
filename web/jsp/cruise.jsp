<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cruises</title>
</head>
<body>
<c:import url="header.jsp"/>
<table border="1" align="center">
    <tr>
        <th>Name</th>
        <th>From</th>
        <th>To</th>
        <th>Ship number</th>
        <th>Cruise status</th>
        <th>Routes</th>
        <th>Actions</th>
    </tr>
    <form action="/CruiseCompany" method="post">
        <input type="hidden" name="command" value="searchCruise">
        <tr>
            <td><input type="text" name="name"></td>
            <td><input type="date" name="from"></td>
            <td><input type="date" name="to"></td>
            <td><input type="text" name="shipNumber"></td>
            <td><select name="cruiseStatusId">
                <option disabled selected>Select cruise status</option>
                <c:forEach var="CruiseStatus" items="${CruiseStatuses}">
                    <option value="${CruiseStatus.id}">${CruiseStatus.name}</option>
                </c:forEach>
            </select></td>
            <td><select name="routesId">
                <option disabled selected>Select route</option>
                <c:forEach var="Route" items="${Routes}">
                    <option value="${Route.id}">${Route.name}</option>
                </c:forEach>
            </select></td>
            <td><input type="submit" value="Search"></td>
        </tr>
    </form>
</table>
<table align="center">
    <tr>
        <td valign="top">
            <table border="1" bgcolor="#7fffd4">
                <caption>Cruises</caption>
                <c:if test="${user.role.id == 1}">
                <tr align="right"><td colspan="10"><a href="/CruiseCompany?command=toCruiseCard"><button>Add</button></a></tr></td>
                </c:if>
                <tr>
                    <th>Name</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Ship number</th>
                    <th>Cruise status</th>
                    <th>Ticket/Seats</th>
                    <th>Routes</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="Cruise" items="${Cruises}" begin="${begin}" end="${end}">
                    <tr>
                        <td>${Cruise.name}</td>
                        <td>${Cruise.from}</td>
                        <td>${Cruise.to}</td>
                        <td>${Cruise.ship.number}</td>
                        <td>${Cruise.status.name}</td>
                        <td>${fn:length(Cruise.tickets)}/${Cruise.ship.numberOfSeats}</td>
                        <td>${Cruise.route.name}</td>
                        <c:if test="${user.role.id == 1}">
                        <td><a href="/CruiseCompany?command=toUpdateCruise&idCruise=${Cruise.id}"><button>Update</button></a>
                            <a href="/CruiseCompany?command=deleteCruise&idCruise=${Cruise.id}"><button>Delete</button></a></td>
                        </c:if>
                        <c:if test="${user.role.id == 2}">
                            <td><a href="/CruiseCompany?command=buyTicket&idCruise=${Cruise.id}"><button>Buy</button></a> </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <tr align="right"><td colspan="10">
                    <c:forEach var="pageNumber" items="${pageNumbers}">
                        <a href="CruiseCompany?command=toCruisePage&pageNumber=${pageNumber}">${pageNumber}</a>
                    </c:forEach>
                </td>
                </tr>
            </table>
        </td>
        <td width="10"></td>
        <td valign="top">
            <c:if test="${user.role.id == 1}">
                <table border="1" bgcolor="#f0ffff">
                    <caption>Cruise Status</caption>
                    <tr>
                        <th>Name</th>
                        <th>Actions</th>
                    </tr>
                    <tr>
                        <c:if test="${SelectedCruiseStatus == null}">
                            <form action="/CruiseCompany" method="post">
                                <input type="hidden" name="command" value="addCruiseStatus">
                                <td><input type="text" name="CruiseStatusName"></td>
                                <td><input type="submit" name="action" value="Add">
                            </form>
                        </c:if>
                        <c:if test="${SelectedCruiseStatus != null}">
                            <form action="/CruiseCompany" method="post">
                                <input type="hidden" name="command" value="updateCruiseStatus">
                                <input type="hidden" name="cruiseStatusId" value="${SelectedCruiseStatus.id}">
                                <td><input type="text" name="CruiseStatusName" value="${SelectedCruiseStatus.name}"></td>
                                <td><input type="submit" name="action" value="update">
                            </form>
                        </c:if>
                        </form>
                    </tr>
                    <c:forEach var="CruiseStatus" items="${CruiseStatuses}">
                        <tr>
                            <td>${CruiseStatus.name}</td>
                            <td><a href="/CruiseCompany?command=updateCruiseStatus&cruiseStatusId=${CruiseStatus.id}"><button>Update</button></a>
                                <a href="/CruiseCompany?command=deleteCruiseStatus&cruiseStatusId=${CruiseStatus.id}"><button>Delete</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>
