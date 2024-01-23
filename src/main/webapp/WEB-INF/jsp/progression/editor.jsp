<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Editor</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <form:form action="/progression/editor/${progressionId}" method="post" modelAttribute="progression">
            <input type="hidden" name="_method" value="patch"/>
            <ol>
                <c:forEach items="${progression.chords}" var="chord">
                    <li>
                        <p>${chord.name}</p>
                        <form:select name="select" path="chords">
                            <c:forEach var="availableChord" items="${availableChords}">
                                <form:option value="${availableChord.id}">
                                    ${availableChord.name}
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </li>
                </c:forEach>
            </ol>
            <button type="submit">Update</button>
        </form:form>
    </tr>
    </tbody>
</table>
</body>
</html>
