<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

<style>
    .confirmation-card {
        background: linear-gradient(135deg, #10b981 0%, #059669 100%);
        color: white;
        border-radius: 15px;
        padding: 40px;
        text-align: center;
        box-shadow: 0 10px 20px rgba(0,0,0,0.2);
        margin-bottom: 30px;
    }
    .confirmation-card .success-icon {
        font-size: 80px;
        margin-bottom: 20px;
        animation: scaleIn 0.5s ease-out;
    }
    @keyframes scaleIn {
        from { transform: scale(0); }
        to { transform: scale(1); }
    }
    .details-card {
        background: white;
        border-radius: 10px;
        padding: 25px;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }
    .detail-row {
        display: flex;
        justify-content: space-between;
        padding: 12px 0;
        border-bottom: 1px solid #e5e7eb;
    }
    .detail-row:last-child {
        border-bottom: none;
    }
    .detail-label {
        font-weight: 600;
        color: #6b7280;
    }
    .detail-value {
        font-weight: 500;
        color: #1f2937;
    }
    .booking-id {
        background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
        color: white;
        padding: 15px 25px;
        border-radius: 10px;
        font-size: 24px;
        font-weight: 700;
        display: inline-block;
        margin: 20px 0;
    }
</style>

<div class="container mt-5">
    <div class="confirmation-card">
        <div class="success-icon">
            <i class="fas fa-check-circle"></i>
        </div>
        <h2 class="mb-3">Booking Confirmed!</h2>
        <p class="mb-0">Your flight has been successfully booked</p>
        <div class="booking-id">
            <i class="fas fa-ticket-alt"></i> Booking ID: ${booking.bookingId}
        </div>
    </div>

    <div class="details-card">
        <h4 class="mb-4"><i class="fas fa-info-circle text-primary"></i> Booking Details</h4>
        
        <div class="detail-row">
            <span class="detail-label"><i class="fas fa-plane"></i> Flight Number</span>
            <span class="detail-value">${flight.flightNumber}</span>
        </div>
        
        <div class="detail-row">
            <span class="detail-label"><i class="fas fa-map-marker-alt"></i> Route</span>
            <span class="detail-value">${flight.source} → ${flight.destination}</span>
        </div>
        
        <div class="detail-row">
            <span class="detail-label"><i class="fas fa-calendar-day"></i> Journey Date</span>
            <span class="detail-value">${booking.journeyDate}</span>
        </div>
        
        <div class="detail-row">
            <span class="detail-label"><i class="fas fa-users"></i> Number of Seats</span>
            <span class="detail-value">${booking.seats}</span>
        </div>
        
        <div class="detail-row">
            <span class="detail-label"><i class="fas fa-rupee-sign"></i> Total Price</span>
            <span class="detail-value fs-5 text-success fw-bold">₹ ${booking.totalPrice}</span>
        </div>
    </div>

    <div class="d-flex gap-3 mt-4">
        <a href="${pageContext.request.contextPath}/user/dashboard" class="btn btn-primary btn-lg flex-fill">
            <i class="fas fa-home"></i> Go to Dashboard
        </a>
        <a href="${pageContext.request.contextPath}/user/myBookings" class="btn btn-outline-primary btn-lg flex-fill">
            <i class="fas fa-list"></i> View My Bookings
        </a>
        <button onclick="window.print()" class="btn btn-success btn-lg">
            <i class="fas fa-print"></i> Print
        </button>
    </div>
</div>