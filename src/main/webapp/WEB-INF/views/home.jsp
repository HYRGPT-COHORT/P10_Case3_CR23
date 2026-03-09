<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flight Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f4f7f6; color: #555; }
        .container { max-width: 900px; margin-top: 50px; }
        .search-container { background-color: white; padding: 30px; box-shadow: 0 0 10px rgba(0,0,0,0.1); border-radius: 8px; }
        .header { text-align: center; margin-bottom: 40px; }
    </style>
</head>
<body>
<div class="container">

    <!-- Header -->
    <div class="header">
        <h1 class="display-4">Welcome to Flight Booking System</h1>
        <p class="lead">Find your perfect flight in seconds.</p>
    </div>

    <!-- login/ssign up buttons -->
    <div class="d-flex justify-content-center mb-4">
        <a href="${contextPath}/login" class="btn btn-primary btn-lg me-3">Login</a>
        <a href="${contextPath}/signup" class="btn btn-success btn-lg">Sign Up</a>
    </div>

    <!-- flight search -->
    <div class="search-container">
        <h3 class="text-center mb-4">Search for Flights</h3>
        <form action="${contextPath}/searchFlights" method="get">
            <div class="mb-3">
                <label for="source" class="form-label">Source</label>
                <input type="text" class="form-control" id="source" name="source" placeholder="Enter Source Location" required>
            </div>
            <div class="mb-3">
                <label for="destination" class="form-label">Destination</label>
                <input type="text" class="form-control" id="destination" name="destination" placeholder="Enter Destination Location" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Search Flights</button>
        </form>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>