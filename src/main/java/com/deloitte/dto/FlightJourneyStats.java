package com.deloitte.dto;

import java.util.Date;

/**
 * DTO to hold aggregated flight journey statistics for admin dashboard
 */
public class FlightJourneyStats {
    private Long flightId;
    private String flightNumber;
    private String source;
    private String destination;
    private Date journeyDate;
    private int totalPassengers;  // Sum of all seats booked
    private int totalBookings;    // Count of bookings
    private double totalRevenue;  // Total price collected
    private String departureTime;
    private String arrivalTime;

    // Constructors
    public FlightJourneyStats() {}

    public FlightJourneyStats(Long flightId, String flightNumber, String source, String destination,
                             Date journeyDate, int totalPassengers, int totalBookings, double totalRevenue,
                             String departureTime, String arrivalTime) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.journeyDate = journeyDate;
        this.totalPassengers = totalPassengers;
        this.totalBookings = totalBookings;
        this.totalRevenue = totalRevenue;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    // Getters and Setters
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
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

    public Date getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(Date journeyDate) {
        this.journeyDate = journeyDate;
    }

    public int getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(int totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
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
}
