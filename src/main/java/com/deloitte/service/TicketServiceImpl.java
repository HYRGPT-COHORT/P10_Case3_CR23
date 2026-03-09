package com.deloitte.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.dao.TicketDao;
import com.deloitte.model.Ticket;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public void saveTicket(Ticket ticket) {
        // Generate PNR number if not already set
        if (ticket.getPnrNo() == 0) {
            ticket.setPnrNo(generatePnrNumber());
        }
        ticketDao.saveTicket(ticket);
    }
    
    private long generatePnrNumber() {
        // Generate a 10-digit PNR number (e.g., 1234567890)
        Random random = new Random();
        return 1000000000L + (long)(random.nextDouble() * 9000000000L);
    }

    @Override
    public List<Ticket> getTicketsByBookingId(long bookingId) {
        return ticketDao.getTicketsByBookingId(bookingId);
    }

    @Override
    public List<Ticket> getTicketsByUserId(long userId) {
        return ticketDao.getTicketsByUserId(userId);
    }

    @Override
    public void updateTicketsByBookingId(long bookingId, String status) {
        ticketDao.updateTicketsByBookingId(bookingId, status);
    }
}