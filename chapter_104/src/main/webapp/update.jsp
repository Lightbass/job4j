<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.crud.UserServlet" %>
<%@ page import="ru.job4j.crud.ValidateService" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update</title>
    <% User user = null;
        try {
            user = ValidateService.STORE.findById(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException nfe) {
            %>
    <meta http-equiv="refresh" content="2; url= <%= request.getContextPath() %>/list">
    <%
            UserServlet.LOGGER.error(nfe.getMessage(), nfe);
        } %>
</head>
<body>
    <% if (user == null) { %>
        Invalid ID
    <% } else { %>
    <form action="<%= request.getContextPath() %>/list" method="post">
        Name : <input type="text" name="name" value="<%= user.getName() %>"><br>
        Login : <input type="text" name="login" value="<%= user.getLogin() %>"><br>
        email : <input type="text" name="email" value="<%= user.getEmail() %>"><br>
        <input type="hidden" name="id" value="<%= user.getId() %>">
        <input type="submit" name="action" value="update">
    </form>
    <% } %>
</body>
</html>