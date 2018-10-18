<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
    <meta http-equiv="refresh" content="2; url=${pageContext.servletContext.contextPath}">
</head>
<body>
    <c:choose>
        <c:when test="${param.get('result') eq '1'}">
            Action done
        </c:when>
        <c:otherwise>
            Action error
        </c:otherwise>
    </c:choose>
</body>
</html>