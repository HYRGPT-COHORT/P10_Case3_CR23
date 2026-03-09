
package com.deloitte.service;

import java.util.List;
import com.deloitte.model.Ticket;

public interface TicketService {
    void saveTicket(Ticket ticket);
    List<Ticket> getTicketsByBookingId(long bookingId);
    List<Ticket> getTicketsByUserId(long userId);
    void updateTicketsByBookingId(long bookingId, String status); // Update all tickets for a booking
}
