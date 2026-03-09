package com.deloitte.dao;

import java.util.List;

import com.deloitte.model.Ticket;

public interface TicketDao {
    void saveTicket(Ticket ticket); // Save individual ticket
    List<Ticket> getTicketsByBookingId(long bookingId);
    List<Ticket> getTicketsByUserId(long userId);
    void updateTicketsByBookingId(long bookingId, String status); // Update all tickets for a booking
    
}
