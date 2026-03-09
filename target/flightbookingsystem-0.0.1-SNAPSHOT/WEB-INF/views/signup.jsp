<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign Up - Flight Booking System</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
body { background: #f0f4f8; font-family:'Segoe UI',sans-serif; min-height:100vh; display:flex; justify-content:center; align-items:center; }
.signup-container { background:white; padding:35px 40px; border-radius:10px; box-shadow:0 8px 25px rgba(0,0,0,0.1); width:100%; max-width:400px; }
h2 { text-align:center; margin-bottom:25px; color:#2c3e50; }
label { font-weight:500; color:#34495e; }
input, select { width:100%; padding:10px 12px; border-radius:5px; border:1px solid #ced4da; margin-bottom:18px; }
input:focus, select:focus { border-color:#3498db; box-shadow:0 0 5px rgba(52,152,219,0.3); outline:none; }
.btn-signup { width:100%; padding:12px; background:linear-gradient(135deg,#5dade2,#2e86c1); color:white; border:none; border-radius:5px; font-weight:bold; cursor:pointer; }
.btn-signup:hover { background:linear-gradient(135deg,#2e86c1,#21618c); }
.signup-footer { text-align:center; margin-top:15px; color:#7f8c8d; }
.signup-footer a { color:#3498db; font-weight:bold; text-decoration:none; }
.signup-footer a:hover { text-decoration:underline; }
.error { color:#c0392b; background:#f8d7da; padding:10px; border-radius:5px; margin-bottom:18px; text-align:center; }
.message { color:#27ae60; background:#d4efdf; padding:10px; border-radius:5px; margin-bottom:18px; text-align:center; }
</style>
</head>
<body>
<div class="signup-container">
<h2>Create Account</h2>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<c:if test="${not empty message}">
    <div class="message">${message}</div>
</c:if>

<form action="${contextPath}/registerUser" method="post">

    <div class="mb-3">
        <label>Email</label>
        <input type="email" name="email" placeholder="Enter email" required>
    </div>


    <div class="mb-3">
        <label>Password</label>
        <input type="password" name="password" placeholder="Enter password" minlength="6" required>
    </div>

    <div class="mb-3">
        <label>Confirm Password</label>
        <input type="password" name="confirmPassword" placeholder="Confirm password" required>
    </div>

    <div class="mb-3">
        <label>Role</label>
        <select name="role" class="form-select">
            <option value="USER" selected>User</option>
            <option value="ADMIN">Admin</option>
        </select>
    </div>

    <button type="submit" class="btn-signup">Sign Up</button>
</form>

<div class="signup-footer">
    Already have an account? <a href="${contextPath}/login">Login here</a>
</div>
</div>
</body>
</html>