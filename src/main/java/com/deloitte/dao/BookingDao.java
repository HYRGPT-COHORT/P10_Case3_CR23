package com.deloitte.dao;

import java.util.List;
import java.util.Date;
import com.deloitte.model.Booking;
import com.deloitte.dto.FlightJourneyStats;

public interface BookingDao {
    void saveBooking(Booking booking);
    Booking getBookingById(Long bookingId);
    List<Booking> getBookingsByUserId(Long userId);
	void updateBooking(Booking booking);
	List<Booking> getAllBookings();
	List<Booking> getUpcomingBookings();
	List<Booking> getCompletedBookings();
	int getTotalBookedSeats(Long flightId, Date journeyDate);
	List<FlightJourneyStats> getUpcomingJourneyStats();
	List<FlightJourneyStats> getCompletedJourneyStats();
}