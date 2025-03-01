
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    <title>Logga in</title>
</head>
<body>

<div class="m-4">
    <h1>Logga in med ditt bibliotekskort</h1>
    <form class="m-4" method="POST" action="/login">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                    ${error}
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success" role="alert">
                    ${message}
            </div>
        </c:if>
        <div class="mb-3">
            <label for="username" class="form-label">Användarnamn: </label>
            <input type="text" id="username" name="username" value="${username}" class="form-control" aria-describedby="usernameHelp">
            <!-- <div id="usernameHelp" class="form-text">Enter your username here!</div> -->
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Lösenord: </label>
            <input type="password" id="password" name="password" class="form-control" aria-describedby="passwordHelp">
            <!-- <div id="passwordHelp" class="form-text">Enter your password here!</div> -->
        </div>

        <button type="submit" class="btn btn-primary">Logga in</button>

        <br>

        <p>
            Har du inget bibliotekskort hos oss än kan du <a href="/register">skaffa ett här</a>.
            Eller om du vill gå <a href="/">tillbaka</a>
        </p>

    </form>
</div>

</body>
</html>
