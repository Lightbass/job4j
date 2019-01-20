<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:if test="${!exists}">
        <meta http-equiv="refresh" content="2; url=${pageContext.servletContext.contextPath}">
    </c:if>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        function validate() {
            var result = "";
            if($('input[id=name]').val() == "") {
                result = result + "Name\n";
            }
            if($('input[id=login]').val() == "") {
                result = result + "Login\n";
            }
            if($('input[id=email]').val() == "") {
                result = result + "Email\n";
            }
            if ($('select[id=country]').val() == "") {
                result = result + "Country\n";
            }
            if ($('select[id=city]').val() == null) {
                result = result + "City\n";
            }
            if(result != "") {
                alert("Заполните остальные поля:\n" + result);
                return false;
            }
            return true;
        }
        function getCities() {
            var country = $('select[id="country"]').val();
            var cities;
            if (country == "russia") {
                cities = ["Saint-Petersburg","Moscow","Pskov"];
            } else if(country == "france") {
                cities = ["Paris","Marseille","Lyon"];
            } else if(country == "japan") {
                cities = ["Tokyo","Kyoto","Hiroshima"];
            } else if(country == "uk") {
                cities = ["London","Manchester","Oxford"];
            }
            $('select[id="city"]').empty();
            cities.forEach(function(item) {
                $('select[id="city"]').append('<option name="city" value=' + item + '>' + item + '</option>');
            });
        }
    </script>
</head>
<body>
<c:choose>
    <c:when test="${!exists}">
        Invalid ID
    </c:when>
    <c:otherwise>
        <div class="container-fluid" style="margin-top: 15px">
            <form action="${pageContext.servletContext.contextPath}/" style="width:300px" method="post">
                <input id="name" type="text" name="name" placeholder="Name" value="${user.name}" class="form-control"><br>
                <input id="login" type="text" name="login" placeholder="Login" value="${user.login}" class="form-control"><br>
                <input id="pwd" type="password" placeholder="Password" name="password" value="${user.password}" class="form-control"><br>
                <input id="email" type="text" name="email" placeholder="email" value="${user.email}" class="form-control"><br>
                Country : <select class="form-control" name="country" id="country" onchange="getCities()">
                <option value="">Select country</option>
                <option value="russia">Russia</option>
                <option value="japan">Japan</option>
                <option value="uk">United Kingdom</option>
                <option value="france">France</option>
                </select><br>
                City : <select class="form-control" name="city" id="city"></select><br>
                <c:choose>
                    <c:when test="${role}">
                            <select class="form-control" name="role">
                                <option value="true">Admin</option>
                                <c:choose>
                                    <c:when test="${user.role}">
                                        <option value="false">user</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option selected value="false">user</option>
                                    </c:otherwise>
                                </c:choose>
                            </select></br>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="role" value="false">
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="id" value="${user.id}">
                <input onclick="return validate();" class="btn btn-primary" type="submit" name="action" value="update">
            </form>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>