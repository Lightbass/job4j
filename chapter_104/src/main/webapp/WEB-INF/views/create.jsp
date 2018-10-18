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
        email : <input type="text" name="email"><br>
        <input type="submit" name="action" value="add">
    </form>
</body>
</html>