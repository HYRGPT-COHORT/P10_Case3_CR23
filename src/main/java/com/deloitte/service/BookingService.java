package com.deloitte.service;

import java.util.List;
import java.util.Date;
import com.deloitte.model.Booking;
import com.deloitte.dto.FlightJourneyStats;

public interface BookingService {
    void createBooking(Booking booking);
    Booking getBookingById(Long bookingId);
    List<Booking> getUserBookings(Long userId);
	void updateBooking(Booking booking);
	List<Booking> getAllBookings();
	List<Booking> getUpcomingBookings();
	List<Booking> getCompletedBookings();
	int getTotalBookedSeats(Long flightId, Date journeyDate);
	List<FlightJourneyStats> getUpcomingJourneyStats();
	List<FlightJourneyStats> getCompletedJourneyStats();
}