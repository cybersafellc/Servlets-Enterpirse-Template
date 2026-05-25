<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib
        prefix="c"
        uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <title>Error</title>

</head>

<body>

<h1>Error</h1>

<h2>

    Status:
    <c:out value="${status}" />

</h2>

<p>

    <c:out value="${message}" />

</p>

</body>

</html>