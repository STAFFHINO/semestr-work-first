<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,900;1,400&display=swap"
          rel="stylesheet">
    <script src="https://kit.fontawesome.com/bd02cb2387.js" crossorigin="anonymous"></script>
    <link href="<c:url value="/resources/css/auth.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
        integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
        crossorigin="anonymous"></script>


<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/"/>"><i class="fa-solid fa-fan"></i> Dragi</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>

<div class="container-sm my-container">
    <form class="px-sm-1 py-sm-0" method="post">
        <h1>Авторизация</h1>
        <div class="md-3">
            <label for="exampleDropdownFormEmail1" class="form-label">Ваш логин</label>
            <input type="text" class="form-control" id="exampleDropdownFormEmail1" name="login" placeholder="login" required>
        </div>
        <div class="mb-sm-6">
            <label for="exampleDropdownFormPassword1" class="form-label">Пароль</label>
            <input type="password" class="form-control" id="exampleDropdownFormPassword1" name="password"
                   placeholder="Password" required>
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
    <div class="dropdown-divider"></div>
    <a href="<c:url value="/registration"/>">Впервые на этом сайте? Зарегистрируйтесь</a>
    <p><a href="<c:url value="/"/>">Продолжить как гость</a></p>
</div>
</body>
</html>
<%--<style>--%>
<%--    html {--%>
<%--        font-size: 16px;--%>
<%--    }--%>

<%--    body {--%>
<%--        font-family: 'Roboto', sans-serif;--%>
<%--        font-size: 16px;--%>
<%--        color: #000;--%>
<%--        background: #fff;--%>
<%--    }--%>

<%--    img {--%>
<%--        max-width: 100%;--%>
<%--    }--%>

<%--    section {--%>
<%--        padding: 50px 0;--%>
<%--    }--%>

<%--    h1 {--%>
<%--        color: blue;--%>
<%--    }--%>


<%--    .my-container {--%>
<%--        width: 500px;--%>
<%--        margin-top: 100px;--%>
<%--    }--%>
<%--</style>--%>