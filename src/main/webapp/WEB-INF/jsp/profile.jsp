<%@ page import="it.omsu.entity.Chord" %>
<%@ page import="java.util.List" %>
<%@ page import="it.omsu.entity.Progression" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<h4>Ваша почта: </h4>
<h4>Ваши сохраненные последовательности:</h4>
<table>
    <tbody>
    <% Set<Progression> progressions = (Set<Progression>) request.getAttribute("progressions"); %>
    <% for (Progression progression : progressions) { %>
    <td><% for (Chord chord : progression.getChords()) { %>
        <%=chord.getName() %>
        <% } %>
    </td>
    <td>
        <form action="/profile/deleteProgression/<%= progression.getId() %>" method="POST">
            <button type="submit">Delete</button>
        </form>
    </td>
    </tr>
    <% } %>
    </tbody>
</table>
<a href="/">На главную</a>
</body>
</html>