package com.deloitte.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.deloitte.model.Ticket;
import com.deloitte.model.User;
import com.deloitte.service.TicketService;

@Controller
@RequestMapping("/user")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/myTickets")
    public String viewTickets(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<Ticket> tickets = ticketService.getTicketsByUserId(user.getUserId());
        model.addAttribute("tickets", tickets);

        return "user/myTickets"; // myTickets.jsp
    }

    @GetMapping("/viewTicket/{bookingId}")
    public String viewTicketByBooking(@PathVariable("bookingId") long bookingId, Model model) {
        List<Ticket> tickets = ticketService.getTicketsByBookingId(bookingId);
        model.addAttribute("tickets", tickets);
        return "user/viewTicket"; // viewTicket.jsp
    }
}

