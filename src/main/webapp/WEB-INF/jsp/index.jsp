<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/main.css">
    <title> Chord Collector</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div>
    <h3> ${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
    <h4><a href ="/login">Войти</a></h4>
    <h4><a href = "/registration">Зарегистрироваться</a></h4>
    </sec:authorize>
    <sec:authorize access = "isAuthenticated()">
        <h4><a href = "/logout">Выйти</a></h4>
    </sec:authorize>
    <h4> <a href = "/collector">Сборщик аккордов</a></h4>
    <sec:authorize access = "hasRole('ROLE_ADMIN')">
    <h4> <a href = "/admin"> Панель админа </a></h4>
    </sec:authorize>
    <sec:authorize access = "isAuthenticated()">
        <h4><a href = "/profile">Мой профиль</a></h4>
    </sec:authorize>
</div>
</body>
</html>