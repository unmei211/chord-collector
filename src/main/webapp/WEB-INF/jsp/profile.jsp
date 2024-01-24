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
    <style>
        <%@include file="../resources/css/main.css"%>
    </style>
</head>
<body>
<h4>Ваша почта: </h4>
<h4>Ваши сохраненные последовательности:</h4>
<table>
    <tbody>
    <c:forEach var="progression" items="${progressions}">
        <tr>
            <td><c:forEach var="chord" items="${progression.chords}">
                ${chord.name}
            </c:forEach></td>

            <td>
                <form:form action="/profile/deleteProgression/${progression.id}">
                    <button type="submit">Delete</button>
                </form:form>
            </td>
            <td>
                <form:form action="/progression/editor/${progression.id}" method="GET">
                    <button type="submit">Edit</button>
                </form:form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<c:url value="/"/>">На главную</a>
</body>
</html>