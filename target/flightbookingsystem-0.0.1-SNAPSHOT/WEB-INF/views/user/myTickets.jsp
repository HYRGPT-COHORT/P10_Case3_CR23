<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

<div class="container mt-5">
    <div class="card shadow-lg border-0 p-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h3 class="mb-0"><i class="fas fa-ticket-alt text-primary"></i> My Tickets</h3>
            <div>
                <a href="${pageContext.request.contextPath}/user/dashboard" class="btn btn-primary btn-sm me-2">
                    <i class="fas fa-home"></i> Dashboard
                </a>
                <a href="${pageContext.request.contextPath}/user/myBookings" class="btn btn-outline-secondary btn-sm">
                    <i class="fas fa-arrow-left"></i> Back to Bookings
                </a>
            </div>
        </div>

        <c:if test="${empty tickets}">
            <div class="alert alert-warning">
                <i class="fas fa-exclamation-triangle"></i> No tickets found.
            </div>
        </c:if>

        <c:if test="${not empty tickets}">
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th><i class="fas fa-hashtag"></i> PNR No</th>
                            <th><i class="fas fa-user"></i> Passenger</th>
                            <th><i class="fas fa-venus-mars"></i> Gender</th>
                            <th><i class="fas fa-birthday-cake"></i> Age</th>
                            <th><i class="fas fa-calendar"></i> Journey Date</th>
                            <th><i class="fas fa-chair"></i> Seat No</th>
                            <th><i class="fas fa-info-circle"></i> Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ticket" items="${tickets}">
                            <tr>
                                <td><span class="badge bg-primary">${ticket.pnrNo}</span></td>
                                <td>${ticket.passengerName}</td>
                                <td>${ticket.gender}</td>
                                <td>${ticket.age}</td>
                                <td>${ticket.journeyDate}</td>
                                <td><span class="badge bg-info">${ticket.seatNo}</span></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${ticket.status == 'CONFIRMED'}">
                                            <span class="badge bg-success">${ticket.status}</span>
                                        </c:when>
                                        <c:when test="${ticket.status == 'CANCELLED'}">
                                            <span class="badge bg-danger">${ticket.status}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-secondary">${ticket.status}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>