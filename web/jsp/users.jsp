<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bolsh
  Date: 13.03.2018
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<c:import url="header.jsp"/>
<table align="center">
    <tr>
        <td>
            <table border="1" bgcolor="#f0f8ff">
                <c:if test="${user.role.id ==1}">
                    <tr><a href="CruiseCompany?command=toUserCard"><button>Add</button></a></tr>
                </c:if>
                <caption>Users</caption>
                <tr>
                    <th>Login</th>
                    <th>Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Ship</th>
                    <c:if test="${user.role.id == 1}">
                    <th>Actions</th>
                    </c:if>
                </tr>
                <c:forEach var="User" items="${Users}">
                    <tr>
                        <td>${User.login}</td>
                        <td>${User.name}</td>
                        <td>${User.lastName}</td>
                        <td>${User.email}</td>
                        <td>${User.role.name}</td>
                        <td>${User.ship.name}</td>
                        <c:if test="${user.role.id == 1}">
                        <td>
                            <form action="CruiseCompany" method="post">
                                <input type="hidden" name="command" value="actionsForUser"/>
                                <input type="hidden" name="idUser" value="${User.id}"/>
                                <input type="submit" name="action" value="Update"/>
                                <input type="submit" name="action" value="Delete"/>
                            </form>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <c:if test="${user.role.id == 1}">
        <td width="100"></td>
        <td>
            <table border="1" bgcolor="#f0ffff">
                <caption>Roles</caption>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                <c:if test="${Role == null || Role.id == 0}">
                    <tr>
                        <form action="CruiseCompany" method="post">
                            <td>
                                <input type="hidden" name="command" value="addRole">
                                <input type="text" name="RoleName">
                            </td>
                            <td>
                                <input type="submit" name="Add" value="Add">
                            </td>
                        </form>
                    </tr>
                </c:if>
                <c:if test="${Role != null && Role.id != null}">
                    <tr>
                        <form action="CruiseCompany" method="post">
                            <td>
                                <input type="hidden" name="command" value="updateRole">
                                <input type="hidden" name="idRole" value="${Role.id}">
                                <input type="text" name="RoleName" value="${Role.name}">
                            </td>
                            <td>
                                <input type="submit" name="Update" value="Update">
                            </td>
                        </form>
                    </tr>
                </c:if>
                <c:forEach var="Role" items="${Roles}">
                    <tr>
                        <td>${Role.name}</td>
                        <td>
                            <form action="CruiseCompany" method="post">
                                <input type="hidden" name="command" value="actionsForRole"/>
                                <input type="hidden" name="idRole" value="${Role.id}"/>
                                <input type="submit" name="action" value="Update"/>
                                <input type="submit" name="action" value="Delete"/>
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
