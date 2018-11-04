<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update</title>
    <c:if test="${!exists}">
        <meta http-equiv="refresh" content="2; url=${pageContext.servletContext.contextPath}">
    </c:if>
</head>
<body>
    <c:choose>
        <c:when test="${!exists}">
            Invalid ID
        </c:when>
        <c:otherwise>
            <form action="${pageContext.servletContext.contextPath}/" method="post">
                Name : <input type="text" name="name" value="${user.name}"><br>
                Login : <input type="text" name="login" value="${user.login}"><br>
                Password : <input type="password" name="password" value="${user.password}"><br>
                email : <input type="text" name="email" value="${user.email}"><br>
                <c:choose>
                    <c:when test="${role}">
                        <select name="role">
                            <option value="true">Admin</option>
                            <c:choose>
                            <c:when test="${user.role}">
                                <option value="false">user</option>
                            </c:when>
                            <c:otherwise>
                                <option selected value="false">user</option>
                            </c:otherwise>
                            </c:choose>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="role" value="false">
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" name="action" value="update">
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>