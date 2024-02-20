
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
        <input type="hidden" name="user" value="${user}">
        <button type="submit">Создать аккорд</button>
    </form:form>
    <form:errors path="name"></form:errors>
    ${nameError}

    <div class="fretboard">
        <div class="frets">

        </div>
        <div class="fret" style="left: 25%;"></div>
        <div class="fret" style="left: 50%;"></div>
        <div class="fret" style="left: 75%;"></div>
        <div class="fret" style="left: 100%;"></div>
        <div class="strings">
            <% for (int i = 0; i < 6; i++) { %>
            <div class="string"></div>
            <% } %>
        </div>
    </div>
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
            <form:select name="select" path="chords">
                <% for (Chord chord : chords) { %>
                <form:option value="<%= chord.getId()%>"><%= chord.getName()%>
                </form:option>
                <%}%>
            </form:select>
        </div>
        <% } %>
        <input type="hidden" name="user" value="${user}">
        <button type="submit">Создать последовательность</button>
    </form:form>
</sec:authorize>
<a href="/">На главную</a>

</body>
</html>

