<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
<body>
    <form action="<%= request.getContextPath() %>/list" method="post">
        Name : <input type="text" name="name"><br>
        Login : <input type="text" name="login"><br>
        email : <input type="text" name="email"><br>
        <input type="submit" name="action" value="add">
    </form>
</body>
</html>