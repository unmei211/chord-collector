<%@ page import="it.omsu.entity.Chord" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
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
        <% List<Chord> chords = (List<Chord>) request.getAttribute("allChords"); %>
        <%-- Цикл для перебора и отображения аккордов --%>
        <% for (Chord chord : chords) { %>
        <tr>
            <td><%= chord.getId() %>
            </td>
            <td><%= chord.getName() %>
            </td>
            </td>
            <td>
                <a href="/collector/update/<%= chord.getId() %>">Edit</a>
                <form action="/collector/delete/<%= chord.getId() %>" method="POST">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <% } %>
    <a href="/">Главная</a>
</div>
</body>
</html>