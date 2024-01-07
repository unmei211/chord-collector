<%@ page import="it.omsu.entity.Chord" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/main.css">
    <meta charset="UTF-8">
    <title>Chords</title>
    <% List<Chord> chords = (List<Chord>) request.getAttribute("allChords"); %>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <%-- Форма для создания аккорда --%>
    <form:form method="POST" action="/collector/create" modelAttribute="chordForm">
        <form:input type="text" path="name" placeholder="Name"></form:input>
        <button type="submit">Создать аккорд</button>
    </form:form>
    <form:errors path="name"></form:errors>
    ${nameError}
</sec:authorize>

<%-- Таблица для отображения списка аккордов --%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <%-- Цикл для перебора и отображения аккордов --%>
    <% for (Chord chord : chords) { %>
    <tr>
        <td><%= chord.getId() %>
        </td>
        <td><%= chord.getName() %>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<sec:authorize access="isAuthenticated()">
    <h4>Создать свою последовательность:</h4>
    <form:form method="POST" action="/collector/createProgression" modelAttribute="progressionForm"
               id="progressionForm">

        <% for (int i = 0; i < 4; i++) {%>
        <div id="chordContainer">
            <!-- Существующий выпадающий список выбора аккорда -->
            <form:select name="select" path="chords">
                <% for (Chord chord : chords) { %>
                <form:option value="<%= chord.getId()%>"><%= chord.getName()%>
                </form:option>
                <%}%>
            </form:select>
        </div>
        <% } %>
        <input type="hidden" name="user" value="${user}"> </input>
        <button type="submit">Создать последовательность</button>
    </form:form>
</sec:authorize>
<a href="/">На главную</a>
</body>
</html>

