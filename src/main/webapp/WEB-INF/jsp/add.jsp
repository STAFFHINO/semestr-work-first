<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add advert</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,900;1,400&display=swap"
        rel="stylesheet">
  <script src="https://kit.fontawesome.com/bd02cb2387.js" crossorigin="anonymous"></script>
  <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
        integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
        crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg fixed-top navbar navbar-dark bg-dark n">
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
            <li><a class="dropdown-item" aria-current="page" href="<c:url value="/add?exit=true"/>">Выйти из аккаунта</a>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
<br>
<br>
<div class="container">
  <main>
    <div class="py-5 text-center">
      <h2>Разместить объявление</h2>
    </div>
    <div class="row g-5">
      <div class="col-md-11 col-lg-12">
        <div class="row g-3">
          <form id="a" action="<c:url value="/add"/>" class="needs-validation" method="post">
            <div class="col-sm-6">
              <label class="form-label" for="category">Категория</label>
              <select class="form-select" name="category" id="category" required="">
                <option selected disabled value="">Выберите категорию из предложенных</option>
                <option>Транспорт</option>
                <option>Недвижимость</option>
                <option>Работа</option>
                <option>Услуги</option>
                <option>Личные вещи</option>
                <option>Для дома и дачи</option>
                <option>Электроника</option>
                <option>Хобби и отдых</option>
                <option>Животные</option>
                <option>Готовый бизнес и оборудование</option>
              </select>
            </div>
            <div class="col-sm-12">
              <label class="form-label" for="name">Название</label>
              <input type="text" class="form-control" name="name" id="name" placeholder="" value=""
                     required>
            </div>
            <div class="col-12">
              <label class="form-label" for="description">Описание</label>
              <input type="text" class="form-control" name="desc" id="description" placeholder=""
                     required>
            </div>
            <div class="col-12">
              <label class="form-label" for="city">Ваш город</label>
              <input type="text" class="form-control" name="city" id="city" placeholder="" required>
              <div class="invalid-feedback">
                Укажите действующий город.
              </div>
            </div>
            <div class="col-12">
              <label class="form-label" for="price">Цена</label>
              <input type="number" step="any" class="form-control" name="price" id="price"
                     placeholder="" value="" required>
            </div>
            <br>
            <button form="a" class="w-100 btn btn-light btn btn-outline-dark btn-lg" type="submit">Разместить
              объявление
            </button>
          </form>
        </div>
      </div>
    </div>
  </main>
</div>
</body>
</html>
