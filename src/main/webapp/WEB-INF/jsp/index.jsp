<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/main.css">
    <title> Chord Collector</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <style>
        <%@include file="../resources/css/main.css"%>
    </style>
</head>
<body>
<div>
    <header class="header">
        <h1 class="header__title">
            <a href="collector">
                Chord Collector
            </a>
            <ul class="title__navigation">
                <li class="navigation__el"><a class="navigation__link" href="collector">Сборщик аккордов</a>
                </li>
                <li class="navigation__el"><a class="navigation__link" href="profile">Мой профиль</a></li>
                <li class="navigation__el"><a class="navigation__link" href="#">Топ аккордов</a></li>
                <li class="navigation__el">
                    <div class="navigation__login">
                        <sec:authorize access="!isAuthenticated()">
                        <a class="navigation__link" href="/login">Войти</a>
                        <a class="navigation__link" href="/registration">Зарегистрироваться</a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                        <a class="navigation__link" href="/logout">Выйти</a>
                        </sec:authorize>
                    </div>
                </li>
            </ul>
        </h1>
    </header>
    <footer>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <h4><a href="/admin"> Панель админа </a></h4>
        </sec:authorize>
    </footer>
</div>
</body>
</html>