<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 05.04.2018
  Time: 00:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User card</title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center" bgcolor="#fff0f5" border="1px ">
    <caption>User Card</caption>
    <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
    <tr>
            <input type="hidden" name="command" value="updateUser"/>
            <input type="hidden" name="idUser" value="${idUser}">

        <td>Login</td>
        <td><c:if test="${user.login != login}">${login}</c:if>
            <c:if test="${user.login == login}"><input type="text" name="login" value="${login}"></c:if></td>
    </tr>
        <c:if test="${user.login == login}">
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>Confirm password</td>
                <td><input type="password" name="passwordConfirm"/></td>
            </tr>
        </c:if>
        <tr>
            <td>Email</td>
            <td><c:if test="${user.login != login}">${email} </c:if>
                <c:if test="${user.login == login}"><input type="text" name="email" value="${email}"></c:if></td>
        </tr>
        <%--<c:if test="${user.role.id != 1}">--%>
        <%--<tr>--%>
            <%--<td>Money</td>--%>
            <%--<td><input type="number" name="money" value="${money}"/></td>--%>
        <%--</tr>--%>
        <%--</c:if>--%>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="${name}"/></td>
        </tr>
    <tr>
        <td>Last name</td>
        <td><input type="text" name="lastName" value="${lastName}"/></td>
    </tr>
<c:if test="${user.role.id == 1}">
    <tr>
        <td>Role</td>
        <td><select name="idRole">
            <c:forEach var="Role" items="${Roles}">
                <c:if test="${Role.id == idRole}">
                    <option selected value="${Role.id}">${Role.name}</option>
                </c:if>
                <c:if test="${Role.id != idRole}">
                    <option value="${Role.id}">${Role.name}</option>
                </c:if>
            </c:forEach>
        </select></td>
    </tr>
</c:if>
    <tr>
        <td>Tickets</td>
        <td>
            <c:forEach var="Ticket" items="${Tickets}">
                <a href="/CruiseCompany?command=toUpdateTicket&idTicket=${Ticket.id}"># ${Ticket.id}</a>
                <a href="/CruiseCompany?command=deleteTicket&idTicket=${Ticket.id}"><button>Delete</button></a><br/>
            </c:forEach>
        </td>
    </tr>
    <tr><td></td><td>
        <input type="submit" value="Update"></td></tr>
    </tr>
    </form>
</table>
<p align="center">${ErrorMassage}</p>
</body>
</html>