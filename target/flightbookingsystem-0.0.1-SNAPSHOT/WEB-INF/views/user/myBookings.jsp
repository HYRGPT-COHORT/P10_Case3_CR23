<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

<div class="container mt-5">
    <div class="card shadow-lg border-0 p-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h3 class="mb-0"><i class="fas fa-calendar-check text-primary"></i> My Bookings</h3>
            <a href="${pageContext.request.contextPath}/user/dashboard" class="btn btn-primary btn-sm">
                <i class="fas fa-home"></i> Back to Dashboard
            </a>
        </div>

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th><i class="fas fa-hashtag"></i> Booking ID</th>
                        <th><i class="fas fa-plane"></i> Flight Number</th>
                        <th><i class="fas fa-calendar"></i> Journey Date</th>
                        <th><i class="fas fa-info-circle"></i> Status</th>
                        <th><i class="fas fa-ticket-alt"></i> View Tickets</th>
                        <th><i class="fas fa-times-circle"></i> Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="booking" items="${bookings}">
                        <tr>
                            <td><span class="badge bg-primary">${booking.bookingId}</span></td>
                            <td>${booking.flightId}</td>
                            <td>${booking.journeyDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${booking.status eq 'CONFIRMED'}">
                                        <span class="badge bg-success">${booking.status}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">${booking.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/user/viewTickets?bookingId=${booking.bookingId}" class="btn btn-info btn-sm">
                                    <i class="fas fa-eye"></i> View Tickets
                                </a>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${booking.status eq 'CONFIRMED'}">
                                        <form action="${pageContext.request.contextPath}/user/cancelBooking" method="post" style="display:inline;">
                                            <input type="hidden" name="bookingId" value="${booking.bookingId}" />
                                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to cancel this booking?');">
                                                <i class="fas fa-ban"></i> Cancel
                                            </button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-secondary btn-sm" disabled>
                                            <i class="fas fa-ban"></i> Cancel
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>






