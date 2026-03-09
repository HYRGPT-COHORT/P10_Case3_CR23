<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Flight Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; padding: 50px; }
        .error-container { max-width: 700px; margin: 0 auto; background: white; padding: 40px; border-radius: 10px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); }
        .error-icon { font-size: 80px; color: #dc3545; text-align: center; }
        h1 { color: #dc3545; text-align: center; margin-top: 20px; }
        .btn-home { margin-top: 30px; }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-icon">⚠️</div>
        <h1>Oops! Something went wrong</h1>
        <div class="alert alert-danger mt-4">
            <strong>Error:</strong> ${errorMessage != null ? errorMessage : 'An unexpected error occurred'}
        </div>
        <c:if test="${not empty url}">
            <p class="text-muted"><small>URL: ${url}</small></p>
        </c:if>
        <div class="text-center">
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-home">Go to Home</a>
        </div>
    </div>
</body>
</html>
