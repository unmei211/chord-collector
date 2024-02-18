<%@ page import="it.omsu.entity.Chord" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/main.css">
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <style>
        <%@include file="../resources/css/main.css"%>
    </style>
</head>
<body>
<div>
    <table>
        <thead>
        <th>ID</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Roles</th>
        </thead>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">${role.name}; </c:forEach>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% List<Chord> chords = (List<Chord>) request.getAttribute("publicChords"); %>
        <%-- Цикл для перебора и отображения аккордов --%>
        <% for (Chord chord : chords) { %>
        <tr>
            <td><%= chord.getId() %>
            </td>
            <td><%= chord.getName() %>
            </td>

            <td>
                <form method="POST" action="admin/update/<%= chord.getId() %>">
                    <div>
                        <input type="text" name="updatename" placeholder="Updatename">
                        <button type="submit">Update</button>
                    </div>
                </form>


                <form action="/admin/delete/<%= chord.getId() %>" method="POST">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <% } %>
    <h2> Создать публичный аккорд</h2>
    <form:form method="POST" action="/admin/createChord" modelAttribute="chordForm">
        <form:input type="text" path="name" placeholder="Name"></form:input>
        <button type="submit">Создать публичный аккорд</button>
    </form:form>
    <form:errors path="name"></form:errors>
    ${nameError}

    <a href="/">Главная</a>
</div>
</body>
</html>