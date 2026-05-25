<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib
        prefix="c"
        uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <title>Register</title>

    <style>

        body {
            font-family: Arial;
            margin: 40px;
        }

        .container {
            width: 400px;
            margin: auto;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }

        button {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
        }

        .error {
            color: red;
        }

        .success {
            color: green;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>Register</h1>

    <c:if test="${not empty success}">

        <p class="success">

            <c:out value="${success}" />

        </p>

    </c:if>

    <form method="post">

        <input
                type="text"
                name="name"
                placeholder="Name"
                required>

        <input
                type="email"
                name="email"
                placeholder="Email"
                required>

        <input
                type="password"
                name="password"
                placeholder="Password"
                required>

        <button type="submit">

            Register

        </button>

    </form>

</div>

</body>

</html>