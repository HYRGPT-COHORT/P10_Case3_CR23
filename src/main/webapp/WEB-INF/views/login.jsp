<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - Flight Booking System</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
    font-family: 'Segoe UI', sans-serif;
    background: linear-gradient(135deg,#667eea,#764ba2);
    min-height: 100vh;
    display:flex; justify-content:center; align-items:center;
}
.login-container {
    background:white;
    border-radius:10px;
    padding:40px;
    box-shadow:0 10px 40px rgba(0,0,0,0.3);
    width:100%; max-width:400px;
}
h1 {
    text-align:center; color:#2c3e50; margin-bottom:30px;
    font-size:28px;
}
.form-group { margin-bottom:20px; }
label { display:block; margin-bottom:8px; color:#2c3e50; font-weight:500; }
input { width:100%; padding:12px; border:1px solid #bdc3c7; border-radius:4px; }
input:focus { outline:none; border-color:#3498db; box-shadow:0 0 5px rgba(52,152,219,0.3); }
.login-button { width:100%; padding:12px; background:linear-gradient(135deg,#667eea,#764ba2); color:white; border:none; border-radius:4px; font-size:16px; font-weight:bold; cursor:pointer; margin-top:10px; }
.login-button:hover { transform:translateY(-2px); }
.signup-link { text-align:center; margin-top:20px; color:#7f8c8d; }
.signup-link a { color:#3498db; font-weight:bold; text-decoration:none; }
.signup-link a:hover { text-decoration:underline; }
.error { color:#e74c3c; padding:12px; background:#fadbd8; border-radius:4px; margin-bottom:20px; text-align:center; }
.message { color:#27ae60; padding:12px; background:#d5f4e6; border-radius:4px; margin-bottom:20px; text-align:center; }
</style>
</head>
<body>

<div class="login-container">
    <h1>Login</h1>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <form method="POST" action="${contextPath}/login">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
        </div>
        <button type="submit" class="login-button">Login</button>
    </form>

    <div class="signup-link">
        Don't have an account? <a href="${contextPath}/signup">Sign up here</a>
    </div>
</div>

</body>
</html>