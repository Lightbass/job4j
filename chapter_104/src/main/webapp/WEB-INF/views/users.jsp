<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
    <table>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <c:out value="${user.name}"></c:out>
                <c:out value="${user.login}"></c:out>
                <c:out value="${user.email}"></c:out>
                <c:out value="${user.id}"></c:out>
                <c:out value="${user.createDate}"></c:out>
            </td>
            <td>
                <form>
                    <button formaction="${pageContext.servletContext.contextPath}/edit" formmethod="get"
                            name="id" value="${user.id}">Edit</button>
                    <button formaction="${pageContext.servletContext.contextPath}/?action=delete&id=${user.id}"
                            formmethod="post">Delete</button>
                </form>
            </td>
        <tr>
        </c:forEach>
    </table>
    <br>
    <form>
        <button formaction="${pageContext.servletContext.contextPath}/create" formmethod="get">Create user</button>
        <button formaction="${pageContext.servletContext.contextPath}/logout" formmethod="get">Logout</button>
    </form>
</body>
</html>