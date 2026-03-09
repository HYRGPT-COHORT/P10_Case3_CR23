<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">

<div class="card shadow-lg border-0">

<div class="card-header bg-primary text-white d-flex justify-content-between">

<div>
<h4 class="mb-0">✈ Flight Search Results</h4>
<small>Select a flight to continue booking</small>
</div>

<span class="badge bg-light text-dark fs-6">
<c:choose>
<c:when test="${empty flights}">
0 Flights
</c:when>
<c:otherwise>
${flights.size()} Flights
</c:otherwise>
</c:choose>
</span>

</div>


<div class="card-body">

<c:choose>

<c:when test="${empty flights}">

<div class="alert alert-warning text-center">
No flights available for selected route.
</div>

</c:when>

<c:otherwise>

<div class="table-responsive">

<table class="table table-hover table-striped align-middle">

<thead class="table-dark">

<tr>
<th>Flight No</th>
<th>Source</th>
<th>Destination</th>
<th>Departure</th>
<th>Arrival</th>
<th>Price</th>
<th>Action</th>
</tr>

</thead>

<tbody>

<c:forEach var="flight" items="${flights}">

<tr>

<td><strong>${flight.flightNumber}</strong></td>

<td>${flight.source}</td>

<td>${flight.destination}</td>

<td>${flight.departureTime}</td>

<td>${flight.arrivalTime}</td>

<td class="fw-bold text-success">
₹ ${flight.pricePerSeat}
</td>

<td>

<a href="${pageContext.request.contextPath}/user/bookFlight?flightId=${flight.flightId}"
class="btn btn-primary btn-sm">

Book Now

</a>

</td>

</tr>

</c:forEach>

</tbody>

</table>

</div>

</c:otherwise>

</c:choose>

</div>

</div>

</div>