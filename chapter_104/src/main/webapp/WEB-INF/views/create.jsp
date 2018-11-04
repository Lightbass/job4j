<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
<body>
    <form action="${pageContext.servletContext.contextPath}/" method="post">
        Name : <input type="text" name="name"><br>
        Login : <input type="text" name="login"><br>
        Password : <input type="password" name="password"><br>
        email : <input type="text" name="email"><br>
        <c:choose>
            <c:when test="${role}">
                <select name="role">
                    <option value="true">Admin</option>
                    <option value="false">user</option>
                </select>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="role" value="false">
            </c:otherwise>
        </c:choose>
        <input type="submit" name="action" value="add">
    </form>
</body>
</html>