<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/main.css">
    <meta charset="UTF-8">
    <title>Create/Edit Chord</title>
    <style>
        <%@include file="../resources/css/main.css"%>
    </style>
</head>
<body>
<h1>Create/Edit Chord</h1>
<form action="<c:url value='/collector/save' />" method="post">
    <input type="hidden" name="id" value="${chord.id}"/>
    <input type="text" name="name" value="${chord.name}"/>
    <!-- Другие поля аккорда -->
    <button type="submit">Save</button>
</form>
</body>
</html>
