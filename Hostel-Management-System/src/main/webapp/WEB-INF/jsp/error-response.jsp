
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>

    <style>
        body {
            font-family: Arial;
            background: #f4f6f9;
            text-align: center;
            padding: 80px;
        }

        .error-box {
            background: white;
            padding: 35px;
            max-width: 500px;
            margin: auto;
            border-radius: 10px;
            box-shadow: 0 2px 10px #ccc;
        }

        h1 {
            color: #dc3545;
        }

        .error-details {
            color: #555;
            margin-top: 20px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>

<body>

    <div class="error-box">

        <h1>Error ${error.status}</h1>

        <h3>${error.error}</h3>

        <p class="error-details">
            ${error.message}
        </p>

        <p>Requested URL: ${error.path}</p>

        <a href="${backUrl}">
    Back to Previous Page
</a>

    </div>

</body>
</html>
