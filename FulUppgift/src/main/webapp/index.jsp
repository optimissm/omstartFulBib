<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    <title>Fulköpings Bibliotek</title>
</head>

<body>
    <div class="m-5">
        <h1><%= "Välkommen till Fulköpings bibliotek!" %></h1>
        <br>
        <p>
            Jag vill:
            <ul>
                <a href="/login"> Logga in </a>  <br>
                <a href="/register"> Registrera mig</a> som ny användare <br>
                Eller fortsätta utforska biblioteket.
            </ul>
        </p>
    </div>
    <div class="m-5">
        <h2><%= "Våra böcker: " %></h2>
        <p>
            <a href="booklist">Lista på våra böcker</a>
        </p>
    </div>

</body>
</html>