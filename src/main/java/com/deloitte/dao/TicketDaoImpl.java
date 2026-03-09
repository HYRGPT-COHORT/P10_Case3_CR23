package com.deloitte.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.deloitte.model.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveTicket(Ticket ticket) {
        // Make sure table names match your DB
        String sql = "INSERT INTO ticket (pnr_no, passenger_name, gender, age, journey_date, seat_no, booking_id, status) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                ticket.getPnrNo(),
                ticket.getPassengerName(),
                ticket.getGender(),
                ticket.getAge(),
                ticket.getJourneyDate(),
                ticket.getSeatNo(),
                ticket.getBookingId(),
                ticket.getStatus());
    }

    @Override
    public List<Ticket> getTicketsByBookingId(long bookingId) {
        String sql = "SELECT * FROM ticket WHERE booking_id = ?";
        return jdbcTemplate.query(sql, new Object[]{bookingId}, new TicketRowMapper());
    }

    @Override
    public List<Ticket> getTicketsByUserId(long userId) {
        // Updated to join with singular 'booking' table
        String sql = "SELECT t.* FROM ticket t " +
                     "JOIN booking b ON t.booking_id = b.booking_id " +
                     "WHERE b.user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new TicketRowMapper());
    }

//    @Override
//    public void updateTicketsByBookingId(long bookingId, String status) {
//        String sql = "UPDATE ticket SET status = ? WHERE booking_id = ?";
//        jdbcTemplate.update(sql, status, bookingId);
//    }
    @Override
    public void updateTicketsByBookingId(long bookingId, String status) {
        String sql = "UPDATE ticket SET status = ? WHERE booking_id = ?";
        jdbcTemplate.update(sql, status, bookingId);
    }

    private static class TicketRowMapper implements RowMapper<Ticket> {
        @Override
        public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ticket t = new Ticket();
            t.setPnrNo(rs.getLong("pnr_no"));
            t.setPassengerName(rs.getString("passenger_name"));
            t.setGender(rs.getString("gender"));
            t.setAge(rs.getInt("age"));
            t.setJourneyDate(rs.getDate("journey_date"));
            t.setSeatNo(rs.getString("seat_no"));
            t.setBookingId(rs.getLong("booking_id"));
            t.setStatus(rs.getString("status"));
            return t;
        }
    }
}