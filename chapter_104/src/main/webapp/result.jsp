<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
    <meta http-equiv="refresh" content="2; url= <%= request.getContextPath() %>">
</head>
<body>
    <% if (request.getParameter("result").equals("1")) { %>
        Action done
    <% } else { %>
        Action error
    <% } %>
</body>
</html>