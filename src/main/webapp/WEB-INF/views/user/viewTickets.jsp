<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Tickets</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 30px 0;
        }
        .ticket-card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            margin-bottom: 25px;
            overflow: hidden;
        }
        .ticket-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px 25px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .ticket-header h5 {
            margin: 0;
            font-size: 1.2rem;
        }
        .ticket-body {
            padding: 25px;
        }
        .info-row {
            display: flex;
            justify-content: space-between;
            padding: 12px 0;
            border-bottom: 1px dashed #dee2e6;
        }
        .info-row:last-child {
            border-bottom: none;
        }
        .info-label {
            color: #6c757d;
            font-weight: 500;
            font-size: 0.9rem;
        }
        .info-value {
            color: #212529;
            font-weight: 600;
        }
        .pnr-badge {
            background: #ffc107;
            color: #000;
            padding: 5px 15px;
            border-radius: 20px;
            font-weight: bold;
            font-size: 1rem;
        }
        .status-badge {
            padding: 5px 15px;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: 600;
        }
        .status-confirmed {
            background-color: #28a745;
            color: white;
        }
        .status-cancelled {
            background-color: #dc3545;
            color: white;
        }
        .flight-route {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px 0;
            background: #f8f9fa;
            border-radius: 10px;
            margin: 15px 0;
        }
        .route-city {
            font-size: 1.5rem;
            font-weight: bold;
            color: #495057;
        }
        .route-arrow {
            margin: 0 20px;
            color: #667eea;
            font-size: 1.5rem;
        }
        .section-title {
            font-size: 1.1rem;
            font-weight: 600;
            color: #495057;
            margin-top: 20px;
            margin-bottom: 15px;
            border-left: 4px solid #667eea;
            padding-left: 10px;
        }
        .back-btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            padding: 12px 30px;
            border-radius: 25px;
            font-weight: 600;
            text-decoration: none;
            display: inline-block;
            transition: transform 0.2s;
        }
        .back-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-white"><i class="fas fa-ticket-alt"></i> My Flight Tickets</h2>
        <div>
            <a href="${pageContext.request.contextPath}/user/dashboard" class="back-btn me-2">
                <i class="fas fa-home"></i> Dashboard
            </a>
            <a href="${pageContext.request.contextPath}/user/myBookings" class="back-btn">
                <i class="fas fa-arrow-left"></i> Back to Bookings
            </a>
        </div>
    </div>

    <c:if test="${empty tickets}">
        <div class="alert alert-warning">
            <i class="fas fa-exclamation-triangle"></i> No tickets found for this booking.
        </div>
    </c:if>

    <c:if test="${not empty tickets}">
        <!-- Booking Information Card -->
        <div class="ticket-card mb-4">
            <div class="ticket-header">
                <h5><i class="fas fa-info-circle"></i> Booking Details</h5>
                <span class="status-badge ${booking.status == 'CONFIRMED' ? 'status-confirmed' : 'status-cancelled'}">
                    ${booking.status}
                </span>
            </div>
            <div class="ticket-body">
                <div class="flight-route">
                    <div class="route-city">${flight.source}</div>
                    <div class="route-arrow"><i class="fas fa-plane"></i></div>
                    <div class="route-city">${flight.destination}</div>
                </div>
                
                <div class="row">
                    <div class="col-md-6">
                        <div class="info-row">
                            <span class="info-label"><i class="fas fa-hashtag"></i> Booking ID:</span>
                            <span class="info-value">${booking.bookingId}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label"><i class="fas fa-plane-departure"></i> Flight Number:</span>
                            <span class="info-value">${flight.flightNumber}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label"><i class="fas fa-calendar-day"></i> Journey Date:</span>
                            <span class="info-value"><fmt:formatDate value="${booking.journeyDate}" pattern="dd MMM yyyy" /></span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="info-row">
                            <span class="info-label"><i class="fas fa-clock"></i> Departure Time:</span>
                            <span class="info-value">${flight.departureTime}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label"><i class="fas fa-clock"></i> Arrival Time:</span>
                            <span class="info-value">${flight.arrivalTime}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label"><i class="fas fa-rupee-sign"></i> Total Price:</span>
                            <span class="info-value">₹ ${booking.totalPrice}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Individual Tickets -->
        <h4 class="text-white mb-3"><i class="fas fa-users"></i> Passenger Details (${tickets.size()} Ticket<c:if test="${tickets.size() > 1}">s</c:if>)</h4>
        
        <c:forEach var="ticket" items="${tickets}" varStatus="status">
            <div class="ticket-card">
                <div class="ticket-header">
                    <h5><i class="fas fa-user"></i> Passenger ${status.index + 1}</h5>
                    <span class="pnr-badge">PNR: ${ticket.pnrNo}</span>
                </div>
                <div class="ticket-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="info-row">
                                <span class="info-label"><i class="fas fa-user-circle"></i> Passenger Name:</span>
                                <span class="info-value">${ticket.passengerName}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-label"><i class="fas fa-venus-mars"></i> Gender:</span>
                                <span class="info-value">${ticket.gender}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-label"><i class="fas fa-birthday-cake"></i> Age:</span>
                                <span class="info-value">${ticket.age} years</span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="info-row">
                                <span class="info-label"><i class="fas fa-chair"></i> Seat Number:</span>
                                <span class="info-value">${ticket.seatNo}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-label"><i class="fas fa-calendar-alt"></i> Journey Date:</span>
                                <span class="info-value"><fmt:formatDate value="${ticket.journeyDate}" pattern="dd MMM yyyy" /></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label"><i class="fas fa-check-circle"></i> Status:</span>
                                <span class="status-badge ${ticket.status == 'CONFIRMED' ? 'status-confirmed' : 'status-cancelled'}">
                                    ${ticket.status}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
