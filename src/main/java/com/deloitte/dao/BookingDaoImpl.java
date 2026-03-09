package com.deloitte.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.deloitte.mapper.BookingRowMapper;
import com.deloitte.model.Booking;
import com.deloitte.dto.FlightJourneyStats;


@Repository
public class BookingDaoImpl implements BookingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveBooking(Booking booking) {
        String sql = "INSERT INTO booking (booking_date, journey_date, seats, total_price, status, flight_id, user_id) VALUES (?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(booking.getBookingDate().getTime()));
            ps.setDate(2, new java.sql.Date(booking.getJourneyDate().getTime()));
            ps.setInt(3, booking.getSeats());
            ps.setDouble(4, booking.getTotalPrice());
            ps.setString(5, booking.getStatus());
            ps.setLong(6, booking.getFlightId());
            ps.setLong(7, booking.getUserId());
            return ps;
        }, keyHolder);

        // Set the generated ID back to booking object
        booking.setBookingId(keyHolder.getKey().longValue());
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        String sql = "SELECT * FROM booking WHERE booking_id=?";
        List<Booking> list = jdbcTemplate.query(sql, new Object[]{bookingId}, new BookingRowMapper());
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        String sql = "SELECT * FROM booking WHERE user_id=?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new BookingRowMapper());
    }
    

@Override
public void updateBooking(Booking booking) {
    String sql = "UPDATE booking SET status=? WHERE booking_id=?";
    jdbcTemplate.update(sql, booking.getStatus(), booking.getBookingId());
}

@Override
public List<Booking> getAllBookings() {
    String sql = "SELECT * FROM booking ORDER BY journey_date DESC, booking_date DESC";
    return jdbcTemplate.query(sql, new BookingRowMapper());
}

@Override
public List<Booking> getUpcomingBookings() {
    String sql = "SELECT * FROM booking WHERE journey_date >= CURDATE() ORDER BY journey_date ASC";
    return jdbcTemplate.query(sql, new BookingRowMapper());
}
@Override
public int getTotalBookedSeats(Long flightId, Date journeyDate) {
    String sql = "SELECT COALESCE(SUM(seats), 0) FROM booking WHERE flight_id = ? AND journey_date = ? AND status = 'CONFIRMED'";
    Integer total = jdbcTemplate.queryForObject(sql, new Object[]{flightId, new java.sql.Date(journeyDate.getTime())}, Integer.class);
    return total != null ? total : 0;
}

@Override
public List<Booking> getCompletedBookings() {
    String sql = "SELECT * FROM booking WHERE journey_date < CURDATE() ORDER BY journey_date DESC";
    return jdbcTemplate.query(sql, new BookingRowMapper());
}

//@Override
//public int getTotalBookedSeats(Long flightId, Date journeyDate) {
//    String sql = "SELECT COALESCE(SUM(seats), 0) FROM booking WHERE flight_id = ? AND journey_date = ? AND status = 'CONFIRMED'";
//    Integer total = jdbcTemplate.queryForObject(sql, new Object[]{flightId, new java.sql.Date(journeyDate.getTime())}, Integer.class);
//    return total != null ? total : 0;
//}

@Override
public List<FlightJourneyStats> getUpcomingJourneyStats() {
    String sql = "SELECT f.flight_id, f.flight_number, f.source, f.destination, " +
                 "f.departure_time, f.arrival_time, b.journey_date, " +
                 "SUM(b.seats) as total_passengers, " +
                 "COUNT(b.booking_id) as total_bookings, " +
                 "SUM(b.total_price) as total_revenue " +
                 "FROM booking b " +
                 "JOIN flights f ON b.flight_id = f.flight_id " +
                 "WHERE b.journey_date >= CURDATE() AND b.status = 'CONFIRMED' " +
                 "GROUP BY f.flight_id, f.flight_number, f.source, f.destination, " +
                 "f.departure_time, f.arrival_time, b.journey_date " +
                 "ORDER BY b.journey_date ASC, f.flight_number ASC";
    
    return jdbcTemplate.query(sql, new FlightJourneyStatsRowMapper());
}

@Override
public List<FlightJourneyStats> getCompletedJourneyStats() {
    String sql = "SELECT f.flight_id, f.flight_number, f.source, f.destination, " +
                 "f.departure_time, f.arrival_time, b.journey_date, " +
                 "SUM(b.seats) as total_passengers, " +
                 "COUNT(b.booking_id) as total_bookings, " +
                 "SUM(b.total_price) as total_revenue " +
                 "FROM booking b " +
                 "JOIN flights f ON b.flight_id = f.flight_id " +
                 "WHERE b.journey_date < CURDATE() AND b.status = 'CONFIRMED' " +
                 "GROUP BY f.flight_id, f.flight_number, f.source, f.destination, " +
                 "f.departure_time, f.arrival_time, b.journey_date " +
                 "ORDER BY b.journey_date DESC, f.flight_number ASC";
    
    return jdbcTemplate.query(sql, new FlightJourneyStatsRowMapper());
}

// RowMapper for FlightJourneyStats
private static class FlightJourneyStatsRowMapper implements RowMapper<FlightJourneyStats> {
    @Override
    public FlightJourneyStats mapRow(ResultSet rs, int rowNum) throws SQLException {
        FlightJourneyStats stats = new FlightJourneyStats();
        stats.setFlightId(rs.getLong("flight_id"));
        stats.setFlightNumber(rs.getString("flight_number"));
        stats.setSource(rs.getString("source"));
        stats.setDestination(rs.getString("destination"));
        stats.setDepartureTime(rs.getString("departure_time"));
        stats.setArrivalTime(rs.getString("arrival_time"));
        stats.setJourneyDate(rs.getDate("journey_date"));
        stats.setTotalPassengers(rs.getInt("total_passengers"));
        stats.setTotalBookings(rs.getInt("total_bookings"));
        stats.setTotalRevenue(rs.getDouble("total_revenue"));
        return stats;
    }
}

}