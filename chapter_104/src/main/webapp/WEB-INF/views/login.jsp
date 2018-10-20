<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignIn</title>
</head>
<body>
    <c:if test="${error != ''}">
        <table>
            <tr>
                <td>
                    <div style="background-color: red" >
                        <c:out value="${error}"/>
                    </div>
                </td>
            </tr>
        </table>
    </c:if>
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        Login : <input type="text" name="login"><br>
        Password : <input type="password" name="password"><br>
        <input type="submit" name="action" value="auth">
    </form>
    <form>
        <button formaction="${pageContext.servletContext.contextPath}/create" formmethod="get">Register</button>
    </form>
</body>
</html>