package com.deloitte.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

public class Flight {
	private long flightId;
	
	@NotBlank(message = "Flight number is required")
	private String flightNumber;
	
	@NotBlank(message = "Source is required")
	private String source;
	
	@NotBlank(message = "Destination is required")
	private String destination;
	
	@NotBlank(message = "Departure time is required")
	private String departureTime; // Format: HH:mm (e.g., "10:30")
	
	@NotBlank(message = "Arrival time is required")
	private String arrivalTime; // Format: HH:mm (e.g., "14:30")
	
	@Min(value = 1, message = "Capacity must be at least 1")
	private int capacity;
	
	@Positive(message = "Price must be positive")
	private double pricePerSeat;
	public long getFlightId() {
		return flightId;
	}
	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPricePerSeat() {
		return pricePerSeat;
	}

	public void setPricePerSeat(double pricePerSeat) {
		this.pricePerSeat = pricePerSeat;
	}

	
}
