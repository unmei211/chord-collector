<%@ page import="it.omsu.entity.Chord" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chords</title>
</head>
<body>
<%-- Форма для создания аккорда --%>
<form action="/collector/create" method="POST">
    <input type="text" name="name" placeholder="Name">
    <button type="submit">Create Chord</button>
</form>

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
