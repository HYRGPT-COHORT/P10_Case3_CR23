<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Flight</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body { font-family:'Segoe UI', sans-serif; background:#f8f9fa; }
.container { max-width:600px; margin:50px auto; background:#fff; padding:30px; border-radius:10px; box-shadow:0 5px 20px rgba(0,0,0,0.1); }
h2 { text-align:center; margin-bottom:30px; color:#343a40; }
.form-label { font-weight:600; }
.btn-submit { width:100%; background:#0d6efd; color:white; font-weight:bold; }
.btn-submit:hover { background:#0b5ed7; }
.error-message { color: #dc3545; font-size: 0.875rem; margin-top: 0.25rem; }
.alert-danger { margin-bottom: 20px; }
</style>
</head>
<body>

<div class="container">
    <h2>Add New Flight</h2>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form:form method="POST" action="${pageContext.request.contextPath}/admin/saveFlight" modelAttribute="flight">
        <div class="mb-3">
            <label class="form-label">Flight Number <span class="text-danger">*</span></label>
            <form:input path="flightNumber" class="form-control" placeholder="e.g., AI101" />
            <form:errors path="flightNumber" cssClass="error-message" />
        </div>

        <div class="mb-3">
            <label class="form-label">Source <span class="text-danger">*</span></label>
            <form:input path="source" class="form-control" placeholder="e.g., Mumbai" />
            <form:errors path="source" cssClass="error-message" />
        </div>

        <div class="mb-3">
            <label class="form-label">Destination <span class="text-danger">*</span></label>
            <form:input path="destination" class="form-control" placeholder="e.g., Delhi" />
            <form:errors path="destination" cssClass="error-message" />
        </div>

        <div class="mb-3">
            <label class="form-label">Departure Time <span class="text-danger">*</span></label>
            <form:input path="departureTime" class="form-control" type="time" placeholder="e.g., 10:30" step="60" />
            <small class="text-muted">Use 24-hour format (e.g., 14:30 for 2:30 PM)</small>
            <form:errors path="departureTime" cssClass="error-message" />
        </div>

        <div class="mb-3">
            <label class="form-label">Arrival Time <span class="text-danger">*</span></label>
            <form:input path="arrivalTime" class="form-control" type="time" placeholder="e.g., 14:30" step="60" />
            <small class="text-muted">Use 24-hour format (e.g., 22:00 for 10:00 PM)</small>
            <form:errors path="arrivalTime" cssClass="error-message" />
        </div>

        <div class="mb-3">
            <label class="form-label">Capacity <span class="text-danger">*</span></label>
            <form:input path="capacity" class="form-control" type="number" min="1" placeholder="Default: 20" />
            <form:errors path="capacity" cssClass="error-message" />
        </div>

        <div class="mb-3">
            <label class="form-label">Price per Seat (₹) <span class="text-danger">*</span></label>
            <form:input path="pricePerSeat" class="form-control" type="number" min="0" step="0.01" placeholder="e.g., 5000" />
            <form:errors path="pricePerSeat" cssClass="error-message" />
        </div>

        <button type="submit" class="btn btn-submit">Add Flight</button>
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary w-100 mt-2">Back to Dashboard</a>
    </form:form>
</div>

</body>
</html>