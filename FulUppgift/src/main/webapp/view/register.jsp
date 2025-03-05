
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrera ny användare</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
<div class="m-5">
<h1>Skaffa ett bibliotekskort hos oss i Fulköping.</h1>

<c:if test="${empty success}">

    <form method="POST" action="/register">
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>

        <div class="mb-3">
            <label for="name" class="form-label">Namn: </label>
            <input type="text" class="form-control" id="name" aria-describedby="nameHelp" name="name" value="${name}">
        </div>

        <div class="mb-3">
            <label for="username" class="form-label">Användarnamn: </label>
            <input type="text" class="form-control" id="username" aria-describedby="usernameHelp" name="username" value="${username}">
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Lösenord: </label>
            <input type="password" class="form-control" id="password" name="password" value="${password}">
        </div>
        <div class="mb-3">
            <label for="password2" class="form-label">Bekräfta lösenord: </label>
            <input type="password" class="form-control" id="password2" name="password2" value="${password2}">
        </div>
        <button type="submit" class="btn btn-primary">Skapa bibliotekskort</button>
    </form>
    <p>Om du redan har ett bibliotekskort kan du <a href="/view/login.jsp">logga in här</a>.</p>
</c:if>
<c:if test="${not empty success}">
    <div class="alert alert-alert alert-success" role="alert">
            ${success}
    </div>
    <p>
        <a href="login">Click here to login!</a>
    </p>
</c:if>

</div>

</body>
</html>


