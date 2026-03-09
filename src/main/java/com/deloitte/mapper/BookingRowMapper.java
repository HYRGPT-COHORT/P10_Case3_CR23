package com.deloitte.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.deloitte.model.Booking;

public class BookingRowMapper implements RowMapper<Booking> {

    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getLong("booking_id"));
        booking.setBookingDate(rs.getDate("booking_date"));
        booking.setJourneyDate(rs.getDate("journey_date"));
        booking.setSeats(rs.getInt("seats"));
        booking.setTotalPrice(rs.getDouble("total_price"));
        booking.setStatus(rs.getString("status"));
        booking.setFlightId(rs.getLong("flight_id"));
        booking.setUserId(rs.getLong("user_id"));
        return booking;
    }
}