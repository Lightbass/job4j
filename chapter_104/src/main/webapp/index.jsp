<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.crud.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
    <table>
        <% for (User user : ValidateService.STORE.findAll()) {%>
        <tr>
            <td>
                Name = <%= user.getName() %>,
                Login = <%= user.getLogin() %>,
                e-mail = <%= user.getEmail() %>,
                id = <%= user.getId() %>,
                date = <%= user.getCreateDate() %>
            </td>
            <td>
                <form>
                    <button formaction="<%= request.getContextPath() %>/update.jsp" formmethod="get"
                    name="id" value="<%= user.getId() %>">Edit</button>
                    <button formaction="<%= request.getContextPath() %>/list?action=delete&id=<%= user.getId() %>"
                            formmethod="post">Delete</button>
                </form>
            </td>
        <tr>
        <% } %>
    </table>
    <br>
    <form>
        <button formaction="<%= request.getContextPath() %>/create.jsp" formmethod="get">Create user</button>
    </form>
</body>
</html>