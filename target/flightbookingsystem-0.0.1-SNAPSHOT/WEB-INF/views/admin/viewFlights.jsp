
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Bookings</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<style>
body { font-family:'Segoe UI',sans-serif; background:#f0f2f5; padding:30px; }
.nav-tabs .nav-link.active { background-color: #0d6efd; color: white; font-weight: bold; }
.nav-tabs .nav-link { color: #495057; font-weight: 500; }
table { background:white; border-radius:10px; overflow:hidden; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
th { background:#2c3e50; color:white; padding: 15px; font-size: 0.9rem; }
td { padding: 12px; vertical-align: middle; }
.status-badge { padding: 5px 12px; border-radius: 20px; font-size: 0.85rem; font-weight: 600; }
.status-confirmed { background-color: #28a745; color: white; }
.status-cancelled { background-color: #dc3545; color: white; }
.table-hover tbody tr:hover { background-color: #f8f9fa; }
</style>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2><i class="fas fa-plane-arrival"></i> Flight Journey Status - All Bookings</h2>
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> Back to Dashboard
        </a>
    </div>
    
    <!-- Tabs for Upcoming and Completed Journeys -->
    <ul class="nav nav-tabs mb-4" id="flightTabs" role="tablist">
        <li class="nav-item" role="presentation">
            <a class="nav-link ${activeTab == 'upcoming' || empty activeTab ? 'active' : ''}" 
               href="${pageContext.request.contextPath}/admin/flights?tab=upcoming">
                <i class="fas fa-clock"></i> Upcoming Journeys
            </a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link ${activeTab == 'completed' ? 'active' : ''}" 
               href="${pageContext.request.contextPath}/admin/flights?tab=completed">
                <i class="fas fa-check-circle"></i> Completed Journey
            </a>
        </li>
    </ul>

    <div class="tab-content" id="flightTabContent">
        <c:if test="${empty bookingDetails}">
            <div class="alert alert-info">
                <i class="fas fa-info-circle"></i> No ${activeTab == 'completed' ? 'completed' : 'upcoming'} bookings found.
            </div>
        </c:if>
        
        <c:if test="${not empty bookingDetails}">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><i class="fas fa-hashtag"></i> Booking ID</th>
                            <th><i class="fas fa-user"></i> Passenger Email</th>
                            <th><i class="fas fa-plane"></i> Flight Number</th>
                            <th><i class="fas fa-map-marker-alt"></i> Route</th>
                            <th><i class="fas fa-calendar-day"></i> Journey Date</th>
                            <th><i class="fas fa-clock"></i> Departure</th>
                            <th><i class="fas fa-users"></i> Seats</th>
                            <th><i class="fas fa-rupee-sign"></i> Total</th>
                            <th><i class="fas fa-info-circle"></i> Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detail" items="${bookingDetails}">
                            <tr>
                                <td><strong>${detail.booking.bookingId}</strong></td>
                                <td>${detail.user.email}</td>
                                <td><span class="badge bg-primary">${detail.flight.flightNumber}</span></td>
                                <td>${detail.flight.source} → ${detail.flight.destination}</td>
                                <td><fmt:formatDate value="${detail.booking.journeyDate}" pattern="dd MMM yyyy" /></td>
                                <td>${detail.flight.departureTime}</td>
                                <td><i class="fas fa-user-friends"></i> ${detail.booking.seats}</td>
                                <td>₹ ${detail.booking.totalPrice}</td>
                                <td>
                                    <span class="status-badge ${detail.booking.status == 'CONFIRMED' ? 'status-confirmed' : 'status-cancelled'}">
                                        <c:choose>
                                            <c:when test="${detail.booking.status == 'CONFIRMED'}">
                                                <i class="fas fa-check"></i> ${detail.booking.status}
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fas fa-times"></i> ${detail.booking.status}
                                            </c:otherwise>
                                        </c:choose>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="mt-3 text-muted">
                <small><i class="fas fa-info-circle"></i> Showing ${bookingDetails.size()} ${activeTab == 'completed' ? 'completed' : 'upcoming'} booking(s)</small>
            </div>
        </c:if>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
