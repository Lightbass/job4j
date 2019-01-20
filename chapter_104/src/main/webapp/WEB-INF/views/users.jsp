<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid" style="margin-top: 15px">
    <table class="table table-hover" style="width: 50%">
        <c:forEach items="${users}" var="user">
        <tr style="vertical-align: middle">
            <td style="vertical-align: middle">
                <c:out value="${user.name}"></c:out>
                <c:out value="${user.login}"></c:out>
                <c:out value="${user.email}"></c:out>
                <c:out value="${user.id}"></c:out>
                <c:out value="${user.createDate}"></c:out>
            </td>
            <c:if test="${role || user.login == login}">
            <td class="col-md-2">
                <form>
                    <div class="col-sm-6">
                        <button formaction="${pageContext.servletContext.contextPath}/edit" formmethod="get"
                                name="id" value="${user.id}" style="margin-left: 15px" class="btn btn-default">Edit
                        </button>
                    </div>

                    <c:if test="${role}">
                        <div class="col-sm-4">
                            <button formaction="${pageContext.servletContext.contextPath}/?action=delete&id=${user.id}"
                                    formmethod="post" class="btn btn-danger">Delete
                            </button>
                        </div>
                    </c:if>
                </form>
            </td>
            </c:if>
        <tr>
            </c:forEach>
    </table>
    <br>
    <form>
        <c:if test="${role}">
            <button formaction="${pageContext.servletContext.contextPath}/create" formmethod="get"
                    class="btn btn-success">Create user
            </button>
        </c:if>
        <button formaction="${pageContext.servletContext.contextPath}/logout" formmethod="get" class="btn btn-warning">
            Logout
        </button>
    </form>
</div>
</body>
</html>