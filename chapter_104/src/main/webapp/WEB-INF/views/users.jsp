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
            <c:if test="${role || user.login == login}">
                <td>
                 <form>
                        <button formaction="${pageContext.servletContext.contextPath}/edit" formmethod="get"
                               name="id" value="${user.id}">Edit</button>
                     <c:if test="${role}">
                        <button formaction="${pageContext.servletContext.contextPath}/?action=delete&id=${user.id}"
                                formmethod="post">Delete</button>
                     </c:if>
                    </form>
                </td>
            </c:if>
        <tr>
        </c:forEach>
    </table>
    <br>
    <form>
        <c:if test="${role}">
            <button formaction="${pageContext.servletContext.contextPath}/create" formmethod="get">Create user</button>
        </c:if>
        <button formaction="${pageContext.servletContext.contextPath}/logout" formmethod="get">Logout</button>
    </form>
</body>
</html>