<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

<style>
    .fixed-logout {
        position: fixed;
        top: 16px;
        right: 16px;
        z-index: 1050;
        box-shadow: 0 8px 20px rgba(0,0,0,0.15);
    }
    .btn-logout { 
        background: #e74c3c; 
        color: white; 
        border: none; 
        padding: 8px 16px; 
        border-radius: 5px; 
    }
    .btn-logout:hover { 
        background: #c0392b; 
        color: white;
    }
    .passenger-card {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        border-radius: 10px;
        padding: 20px;
        margin-bottom: 20px;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }
    .passenger-card h5 {
        font-weight: 600;
        margin-bottom: 20px;
    }
    .form-label {
        font-weight: 500;
        color: rgba(255,255,255,0.9);
    }
    .form-control, .form-select {
        border-radius: 8px;
        border: 2px solid rgba(255,255,255,0.3);
        background: rgba(255,255,255,0.95);
    }
    .form-control:focus, .form-select:focus {
        border-color: #fbbf24;
        box-shadow: 0 0 0 0.2rem rgba(251, 191, 36, 0.25);
    }
</style>

<a href="${pageContext.request.contextPath}/logout" class="btn btn-logout fixed-logout">Logout</a>

<div class="container mt-5">
    <div class="card shadow-lg border-0 p-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h3 class="mb-1"><i class="fas fa-users text-primary"></i> Add Passenger Details</h3>
                <p class="text-muted mb-0">Please provide information for all passengers</p>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/user/dashboard" class="btn btn-outline-secondary btn-sm me-2">
                    <i class="fas fa-home"></i> Dashboard
                </a>
                <a href="${pageContext.request.contextPath}/user/myBookings" class="btn btn-outline-secondary btn-sm">
                    <i class="fas fa-list"></i> My Bookings
                </a>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/user/confirmBooking" method="post">
            <input type="hidden" name="flightId" value="${flightId}" />
            <input type="hidden" name="userId" value="${user.userId}" />
            <input type="hidden" name="journeyDate" value="${journeyDate}" />
            <input type="hidden" name="seats" value="${seats}" />

            <c:forEach var="i" begin="1" end="${seats}">
                <div class="passenger-card">
                    <h5><i class="fas fa-user-circle"></i> Passenger ${i}</h5>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label"><i class="fas fa-id-card"></i> Full Name</label>
                            <input type="text" name="passengerName${i}" class="form-control" placeholder="Enter full name" required />
                        </div>
                        <div class="col-md-3 mb-3">
                            <label class="form-label"><i class="fas fa-birthday-cake"></i> Age</label>
                            <input type="number" name="passengerAge${i}" class="form-control" min="1" max="120" placeholder="Age" required />
                        </div>
                        <div class="col-md-3 mb-3">
                            <label class="form-label"><i class="fas fa-venus-mars"></i> Gender</label>
                            <select name="passengerGender${i}" class="form-select" required>
                                <option value="">Select</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class="d-grid gap-2 mt-4">
                <button type="submit" class="btn btn-success btn-lg">
                    <i class="fas fa-check-circle"></i> Complete Booking
                </button>
            </div>
        </form>
    </div>
</div>
