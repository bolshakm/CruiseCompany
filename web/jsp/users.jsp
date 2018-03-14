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


<table align="center">
    <tr>
        <td>
            <table border="1" bgcolor="#f0f8ff">
                <caption>Users</caption>
                <tr>
                    <th>Login</th>
                    <th>Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="User" items="${Users}">
                    <tr>
                        <td>${User.login}</td>
                        <td>${User.name}</td>
                        <td>${User.lastName}</td>
                        <td>${User.email}</td>
                        <td>${User.role.name}</td>
                        <td></td>

                    </tr>
                </c:forEach>
            </table>
        </td>
        <td width="100"></td>
        <td>
            <table border="1" bgcolor="#f0ffff">
                <caption>Roles</caption>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                <form action="${pageContext.request.contextPath}/CruiseCompany" method="post">
                    <tr>
                        <td><input name="roleName"></td>
                        <td><input type="submit" name="Add" value="Add">
                            <%--<input type="submit" name="Found" value="Found"></td>--%>
                    </tr>
                </form>
                <c:forEach var="Role" items="${Roles}">
                    <tr>
                        <td>${Role.name}</td>
                        <td><a href="CruiseCompany?command=updateRole&selectedRole=${Role.id}">Update</a>
                            <a href="CruiseCompany?command=deleteRole&selectedRole=${Role.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
