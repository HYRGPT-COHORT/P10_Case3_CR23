<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

<style>
    body {
        background-color: #e9ecef;
        font-family: 'Poppins', sans-serif;
    }

    .dashboard-container {
        max-width: 1100px;
        margin: 50px auto;
    }

    .dashboard-header {
        text-align: center;
        margin-bottom: 50px;
    }

    .dashboard-header h1 {
        font-weight: 700;
        color: #343a40;
    }

    .dashboard-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 30px;
    }

    .dashboard-card {
        background: #fff;
        border-radius: 15px;
        padding: 30px 20px;
        box-shadow: 0 8px 20px rgba(0,0,0,0.08);
        text-align: center;
        transition: all 0.3s ease;
        cursor: pointer;
        position: relative;
    }

    .dashboard-card:hover {
        transform: translateY(-8px);
        box-shadow: 0 15px 30px rgba(0,0,0,0.15);
    }

    .dashboard-card i {
        font-size: 3rem;
        margin-bottom: 15px;
        color: #fff;
        padding: 20px;
        border-radius: 50%;
        display: inline-block;
    }

    .card-add i {
        background: #0d6efd; /* Blue */
    }

    .card-view i {
        background: #198754; /* Green */
    }

    .dashboard-card h3 {
        font-weight: 600;
        margin-bottom: 10px;
        color: #343a40;
    }

    .dashboard-card p {
        color: #6c757d;
        font-size: 0.95rem;
        margin-bottom: 0;
    }

</style>
</head>
<body>

<div class="dashboard-container">
    <div class="dashboard-header">
        <h1>Admin Dashboard</h1>
        <p class="text-muted">Manage flights and bookings efficiently</p>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger mt-3">
            <i class="fas fa-sign-out-alt"></i> Logout
        </a>
    </div>

    <div class="dashboard-grid">
        <!-- Add Flight -->
        <div class="dashboard-card card-add" onclick="location.href='${pageContext.request.contextPath}/admin/addFlight'">
            <i class="fa-solid fa-plane-departure"></i>
            <h3>Add New Flight</h3>
            <p>Create new flights and schedules for your airline</p>
        </div>

        <!-- View Flights -->
        <div class="dashboard-card card-view" onclick="location.href='${pageContext.request.contextPath}/admin/flights'">
            <i class="fa-solid fa-list"></i>
            <h3>View Flights</h3>
            <p>View and manage all existing flight schedules and bookings</p>
        </div>
    </div>
</div>

</body>
</html>