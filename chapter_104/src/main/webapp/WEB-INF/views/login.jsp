<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignIn</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<c:if test="${error != ''}">
    <table>
        <tr>
            <td>
                <div style="background-color: red">
                    <c:out value="${error}"/>
                </div>
            </td>
        </tr>
    </table>
</c:if>
<div class="container-fluid" style="margin-top: 15px">
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <div class="form-group">
            <input type="text" name="login" placeholder="Login" style="width: 10%" class="form-control" required><br>
            <input type="password" name="password" placeholder="Password" style="width: 10%" class="form-control" required><br>
            <button type="submit" name="action" value="auth" class="btn btn-success" style="width: 5%" class="form-control">Login</button>
            <button id="register" formaction="${pageContext.servletContext.contextPath}/create" formmethod="get" class="btn btn-info" style="width: 5%" formnovalidate>Register</button>
        </div>
    </form>
</div>
</body>
</html>