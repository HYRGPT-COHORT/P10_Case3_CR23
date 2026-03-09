package com.deloitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.deloitte.dao.BookingDao;
import com.deloitte.model.Booking;
import com.deloitte.dto.FlightJourneyStats;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;
@Autowired
private JdbcTemplate jdbcTemplate;
    @Override
    public void createBooking(Booking booking) {
        bookingDao.saveBooking(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingDao.getBookingById(bookingId);
    }

    @Override
    public List<Booking> getUserBookings(Long userId) {
        return bookingDao.getBookingsByUserId(userId);
    }
    
    
    @Override
   
    public void updateBooking(Booking booking) {
        String sql = "UPDATE booking SET status=? WHERE booking_id=?";
        jdbcTemplate.update(sql, booking.getStatus(), booking.getBookingId());
    }
    
    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }
    
    @Override
    public List<Booking> getUpcomingBookings() {
        return bookingDao.getUpcomingBookings();
    }
    
    @Override
    public List<Booking> getCompletedBookings() {
        return bookingDao.getCompletedBookings();
    }
    
    @Override
    public int getTotalBookedSeats(Long flightId, java.util.Date journeyDate) {
        return bookingDao.getTotalBookedSeats(flightId, journeyDate);
    }
    
    @Override
    public List<FlightJourneyStats> getUpcomingJourneyStats() {
        return bookingDao.getUpcomingJourneyStats();
    }
    
    @Override
    public List<FlightJourneyStats> getCompletedJourneyStats() {
        return bookingDao.getCompletedJourneyStats();
    }
}