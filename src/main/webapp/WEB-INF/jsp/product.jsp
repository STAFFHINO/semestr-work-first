<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,900;1,400&display=swap"
          rel="stylesheet">
    <script src="https://kit.fontawesome.com/bd02cb2387.js" crossorigin="anonymous"></script>
    <link href="<c:url value="/resources/css/edit.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
        integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
        crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg fixed-top navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/"/>"><i class="fa-solid fa-fan"></i> Dragi</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item col-6 col-lg-auto">
                    <a class="nav-link" role="button" href="<c:url value="/basket"/>">
                        <i class="fa-solid fa-basket-shopping"></i>
                        <small class="d-lg-none ms-2">Basket</small>
                    </a>
                </li>
                <li class="nav-item dropdown col-6 col-lg-auto">
                    <a class="nav-link" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <i class="fa-solid fa-user"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" aria-current="page" href="<c:url value="/product?exit=true"/>">Выйти
                            из аккаунта</a>
                        </li>
                        <c:if test="${sessionScope.currentRole != null }">
                            <c:if test="${!(sessionScope.currentRole.equals('seller'))}">
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li>
                                    <button class="dropdown-item" data-bs-toggle="modal"
                                            data-bs-target="#staticBackdrop"
                                            type="submit">Стать продавцом
                                    </button>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.currentRole.equals('seller')}">
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li>
                                    <a href="<c:url value="/products/my-products"/>" class="dropdown-item"
                                       type="submit">Мои продукты</a>
                                </li>
                            </c:if>
                        </c:if>
                    </ul>
                </li>
                <li class="nav-item">
                    <c:if test="${sessionScope.currentRole != null}">
                        <c:if test="${sessionScope.currentRole.equals('seller')}">
                            <a class="btn btn-outline-light" href="<c:url value="/add"/>" type="submit">Разместить
                                объявление</a>
                        </c:if>
                        <c:if test="${!(sessionScope.currentRole.equals('seller'))}">
                            <button class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#needSeller"
                                    type="submit">Разместить объявление
                            </button>
                        </c:if>
                    </c:if>
                    <c:if test="${sessionScope.currentRole == null}">
                        <button class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#needSeller"
                                type="submit">Разместить объявление
                        </button>
                    </c:if>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="main">
    <div class="container">
        <div class="col-md-9 col-sm-7">
            <div class="row">
                <c:if test="${requestScope.name != null}">
                <div>
                    <h4>Продавец: ${requestScope.seller}
                    </h4>
                    <p>Дата добавления: ${requestScope.date}
                    </p>
                </div>
                <div class="col-md-6 col-sm-6">
                    <div class="product-main-image">
                        <img class="card-img-top" alt="Картинка"
                             src="/images/${requestScope.name}"/>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6">
                    <h1>${requestScope.name}
                    </h1>
                    </c:if>
                    <div class="price-availability-block clearfix">
                        <c:if test="${requestScope.price != null}">
                            <div class="price">
                                <strong>${requestScope.price}<span> руб.</span></strong>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.desc != null}">
                            <div class="description">
                                <p>${requestScope.desc}
                                </p>
                            </div>
                        </c:if>
                    </div>
                    <a href="<c:url value="/product?add=${requestScope.name}"/>">
                        <button class="w-100 btn btn-light btn btn-outline-dark btn-lg" type="submit">Добавить в
                            корзину
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">VIP</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Для того, чтобы продавать на этой площадке, нужно оплатить платную подписку за 399 рублей
                <br>
                Реквизиты:
                <br>
                По номеру телефона +79911153704 Сбербанк, в сообщении указать "Хочу быть легендой"
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <a href="<c:url value="/?seller=yes"/>" type="button" class="btn btn-primary">ОК</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="needSeller" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="needSellerLabel">VIP</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Для того, чтобы продавать на этой площадке, нужно приобрести права продавца.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<%--<style>--%>
<%--    .price-availability-block {--%>
<%--        border-bottom: 1px solid #f4f4f4;--%>
<%--        padding-bottom: 12px;--%>
<%--        margin-bottom: 17px;--%>
<%--    }--%>

<%--    .main {--%>
<%--        margin-top: 5rem;--%>
<%--    }--%>
<%--</style>--%>