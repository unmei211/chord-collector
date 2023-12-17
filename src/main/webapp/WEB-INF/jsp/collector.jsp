<%@ page import="it.omsu.entity.Chord" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chords</title>
</head>
<body>
<%-- Форма для создания аккорда --%>
<form:form method="POST" action="/collector/create"  modelAttribute="chordForm">
    <form:input type="text" path="name" placeholder="Name"></form:input>
    <button type="submit">Create Chord</button>
</form:form>
<form:errors path="name"></form:errors>
${nameError}

<%-- Таблица для отображения списка аккордов --%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% List<Chord> chords = (List<Chord>) request.getAttribute("chords"); %>
    <%-- Цикл для перебора и отображения аккордов --%>
    <% for (Chord chord : chords) { %>
    <tr>
        <td><%= chord.getId() %>
        </td>
        <td><%= chord.getName() %>
        </td>
        </td>
        <td>
            <a href="/collector/<%= chord.getId() %>">Details</a>
            <a href="/collector/update/<%= chord.getId() %>">Edit</a>
            <form action="/collector/delete/<%= chord.getId() %>" method="POST">
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
    </tbody>
</table>

<%-- Форма для обновления аккорда --%>
<form action="/collector/update" method="POST">
    <input type="hidden" name="id" value="<%= chord.getId() %>">
    <input type="text" name="name" value="<%= chord.getName() %>">
    <button type="submit">Update Chord</button>
</form>
<% } %>
</body>
</html>
