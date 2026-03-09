<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

<div class="container mt-5">
    <div class="card shadow-lg border-0 p-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h3 class="mb-0">Book Flight - ${flight.flightNumber}</h3>
            <a href="${pageContext.request.contextPath}/user/dashboard" class="btn btn-outline-secondary btn-sm">
                <i class="fas fa-home"></i> Back to Dashboard
            </a>
        </div>
        <p>From ${flight.source} to ${flight.destination}</p>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/user/checkAvailability" method="post">
            <input type="hidden" name="flightId" value="${flight.flightId}" />

            <div class="mb-3">
                <label>Journey Date</label>
                <input type="date" name="journeyDate" class="form-control" placeholder="DD/MM/YYYY">
            </div>

            <div class="mb-3">
                <label>Number of Passengers</label>
                <input type="number" name="seats" class="form-control" min="1" max="${flight.capacity}" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Check Availability</button>
        </form>
    </div>
</div>