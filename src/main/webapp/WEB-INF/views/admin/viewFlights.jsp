
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Management</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<style>
body { font-family:'Segoe UI',sans-serif; background:#f0f2f5; padding:30px; }
.nav-tabs .nav-link.active { background-color: #0d6efd; color: white; font-weight: bold; }
.nav-tabs .nav-link { color: #495057; font-weight: 500; }
table { background:white; border-radius:10px; overflow:hidden; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
th { background:#2c3e50; color:white; padding: 15px; font-size: 0.9rem; }
td { padding: 12px; vertical-align: middle; }
.table-hover tbody tr:hover { background-color: #f8f9fa; }
.route-badge { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 5px 12px; border-radius: 20px; font-size: 0.85rem; }
</style>
</head>
<body>
<div class="container-fluid">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2><i class="fas fa-plane"></i> Flight Management & Journey Status</h2>
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> Back to Dashboard
        </a>
    </div>
    
    <!-- Tabs for Flights, Upcoming and Completed Journeys -->
    <ul class="nav nav-tabs mb-4" id="flightTabs" role="tablist">
        <li class="nav-item" role="presentation">
            <a class="nav-link ${activeTab == 'flights' || empty activeTab ? 'active' : ''}" 
               href="${pageContext.request.contextPath}/admin/flights?tab=flights">
                <i class="fas fa-list"></i> All Flights
            </a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link ${activeTab == 'upcoming' ? 'active' : ''}" 
               href="${pageContext.request.contextPath}/admin/flights?tab=upcoming">
                <i class="fas fa-clock"></i> Upcoming Journeys
            </a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link ${activeTab == 'completed' ? 'active' : ''}" 
               href="${pageContext.request.contextPath}/admin/flights?tab=completed">
                <i class="fas fa-check-circle"></i> Completed Journeys
            </a>
        </li>
    </ul>

    <div class="tab-content" id="flightTabContent">
        
        <!-- Tab 1: All Flights (Master List) -->
        <c:if test="${activeTab == 'flights' || empty activeTab}">
            <c:if test="${empty flights}">
                <div class="alert alert-info">
                    <i class="fas fa-info-circle"></i> No flights have been added yet.
                </div>
            </c:if>
            
            <c:if test="${not empty flights}">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th><i class="fas fa-hashtag"></i> Flight Number</th>
                                <th><i class="fas fa-map-marker-alt"></i> Source</th>
                                <th><i class="fas fa-map-marker-alt"></i> Destination</th>
                                <th><i class="fas fa-clock"></i> Departure Time</th>
                                <th><i class="fas fa-clock"></i> Arrival Time</th>
                                <th><i class="fas fa-rupee-sign"></i> Price/Seat</th>
                                <th><i class="fas fa-chair"></i> Capacity</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="flight" items="${flights}">
                                <tr>
                                    <td><span class="badge bg-primary fs-6">${flight.flightNumber}</span></td>
                                    <td>${flight.source}</td>
                                    <td>${flight.destination}</td>
                                    <td>${flight.departureTime}</td>
                                    <td>${flight.arrivalTime}</td>
                                    <td class="fw-bold text-success">₹ ${flight.pricePerSeat}</td>
                                    <td>${flight.capacity}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </c:if>
        
        <!-- Tab 2 & 3: Upcoming/Completed Journeys (Aggregated Stats) -->
        <c:if test="${activeTab == 'upcoming' || activeTab == 'completed'}">
            <c:if test="${empty journeyStats}">
                <div class="alert alert-info">
                    <i class="fas fa-info-circle"></i> No ${activeTab} journey bookings found.
                </div>
            </c:if>
            
            <c:if test="${not empty journeyStats}">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th><i class="fas fa-plane"></i> Flight Number</th>
                                <th><i class="fas fa-route"></i> Route</th>
                                <th><i class="fas fa-calendar-day"></i> Journey Date</th>
                                <th><i class="fas fa-clock"></i> Departure</th>
                                <th><i class="fas fa-users"></i> Total Passengers</th>
                                <th><i class="fas fa-ticket-alt"></i> Total Bookings</th>
                                <th><i class="fas fa-rupee-sign"></i> Total Revenue</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="stats" items="${journeyStats}">
                                <tr>
                                    <td><span class="badge bg-primary fs-6">${stats.flightNumber}</span></td>
                                    <td>
                                        <span class="route-badge">
                                            ${stats.source} <i class="fas fa-arrow-right"></i> ${stats.destination}
                                        </span>
                                    </td>
                                    <td><fmt:formatDate value="${stats.journeyDate}" pattern="dd MMM yyyy" /></td>
                                    <td>${stats.departureTime}</td>
                                    <td><i class="fas fa-user-friends"></i> ${stats.totalPassengers}</td>
                                    <td><span class="badge bg-info">${stats.totalBookings}</span></td>
                                    <td class="fw-bold text-success">₹ ${stats.totalRevenue}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </c:if>
        
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
