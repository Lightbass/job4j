<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        function validate() {
            var result = "";
            if ($('input[id=name]').val() == "") {
                result = result + "Name\n";
            }
            if ($('input[id=login]').val() == "") {
                result = result + "Login\n";
            }
            if ($('input[id=pwd]').val() == "") {
                result = result + "Password\n";
            }
            if ($('input[id=email]').val() == "") {
                result = result + "Email\n";
            }
            if ($('select[id=country]').val() == "") {
                result = result + "Country\n";
            }
            if ($('select[id=city]').val() == null) {
                result = result + "City\n";
            }
            if (result != "") {
                alert("Заполните остальные поля:\n" + result);
                return false;
            }
            return true;
        }

        function getCountries() {
            $('select[id="country"]').append('<option name="country" value="0">Select country</option>');
            $.getJSON('countries', function(data){
                data.forEach(function (item) {
                    $('select[id="country"]').append('<option name="country" value=' + item.id + '>' + item.name + '</option>');
                });
            });
        }

        function getCities() {
            $('select[id="city"]').empty();
            $('select[id="city"]').append('<option name="city" value="0">Select city</option>');
            var country = $('select[id="country"]').val();
            $.getJSON('cities?countryId=' + country, function(data){
                data.forEach(function (item) {
                    $('select[id="city"]').append('<option name="city" value=' + item.name + '>' + item.name + '</option>');
                });
            });
        }
        window.onload = getCountries;
    </script>
</head>

<body>
<div class="container-fluid" style="margin-top: 15px">
    <form action="${pageContext.servletContext.contextPath}/" method="post" style="width:300px">
        Name : <input id="name" type="text" name="name" class="form-control">
        Login : <input id="login" type="text" name="login" class="form-control">
        Password : <input id="pwd" type="password" name="password" class="form-control">
        Email : <input id="email" type="text" name="email" class="form-control">
        Country : <select class="form-control" name="country" id="country" onchange="getCities()">
    </select>
        City : <select class="form-control" name="city" id="city"></select><br>
        <c:choose>
            <c:when test="${role}">
                Role :
                <select name="role" class="form-control">
                    <option value="true">Admin</option>
                    <option value="false">user</option>
                </select></br>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="role" value="false">
            </c:otherwise>
        </c:choose>
        <button onclick="return validate();" type="submit" name="action" value="add" class="btn btn-success">Create
        </button>
    </form>
</div>
</body>
</html>