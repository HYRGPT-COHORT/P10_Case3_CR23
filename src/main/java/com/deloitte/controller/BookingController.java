package com.deloitte.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.model.Booking;
import com.deloitte.model.Flight;
import com.deloitte.model.Ticket;
import com.deloitte.model.User;
import com.deloitte.service.BookingService;
import com.deloitte.service.FlightService;
import com.deloitte.service.TicketService;

@Controller
@RequestMapping("/user")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private TicketService ticketService;

    @Autowired
    private FlightService flightService;

    // open booking form
    @GetMapping("/bookFlight") //reqparam
    public String bookFlight(@RequestParam("flightId") long flightId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
        	session.setAttribute("redirectAfterLogin", "/user/bookFlight?flightId="+flightId);
        	
            return "redirect:/login";
        }

        Flight flight = flightService.getFlightById(flightId);
        model.addAttribute("flight", flight);
        model.addAttribute("user", user);
        return "user/bookFlight"; // user/bookFlight.jsp
    }

    // Handle Booking Submission //
    @PostMapping("/checkAvailability")
    public String checkAvailability(@RequestParam("flightId") Long flightId,
                                    @RequestParam("journeyDate") String journeyDateStr,
                                    @RequestParam("seats") int seats,
                                    HttpSession session,
                                    Model model) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            session.setAttribute("redirectFlightId", flightId);
            session.setAttribute("redirectSeats", seats);
            session.setAttribute("redirectJourneyDate", journeyDateStr);
            return "redirect:/login";
        }

        Flight flight = flightService.getFlightById(flightId);

        // Parse the date manually
        Date journeyDate = null;
        try {
            journeyDate = (Date) new java.text.SimpleDateFormat("yyyy-MM-dd").parse(journeyDateStr);
        } catch (java.text.ParseException e) {
            logger.error("Invalid date format: {}", journeyDateStr, e);
            model.addAttribute("error", "Invalid date format");
            model.addAttribute("flight", flight);
            return "user/bookFlight";
        }

        // CHECK SEAT AVAILABILITY HERE (CRITICAL FIX)
        if (flight == null) {
            model.addAttribute("error", "Flight not found");
            return "user/bookFlight";
        }
        
        // Get total already booked seats for this flight on this date
        int bookedSeats = bookingService.getTotalBookedSeats(flightId, journeyDate);
        int availableSeats = flight.getCapacity() - bookedSeats;
        
        logger.info("Flight {} on {}: Capacity={}, Booked={}, Available={}, Requested={}", 
                    flightId, journeyDateStr, flight.getCapacity(), bookedSeats, availableSeats, seats);
        
        if (seats > availableSeats) {
            logger.warn("Insufficient seats. Requested: {}, Available: {}", seats, availableSeats);
            model.addAttribute("error", "Not enough seats available! Only " + availableSeats + " seats remaining for this date.");
            model.addAttribute("flight", flight);
            model.addAttribute("user", user);
            return "user/bookFlight";
        }

        session.setAttribute("bookingFlightId", flightId);
        session.setAttribute("bookingSeats", seats);
        session.setAttribute("bookingJourneyDate", journeyDate);

        logger.info("Seat availability confirmed for user: {}, flight: {}, seats: {}", user.getUserId(), flightId, seats);
        return "redirect:/user/addPassenger";
    }
    
//    @PostMapping("/confirmBooking")
//    public String confirmBooking(HttpSession session,
//                                 @RequestParam Map<String, String> params,
//                                 Model model) {
//
//        Long flightId = (Long) session.getAttribute("bookingFlightId");
//        Long userId = ((User) session.getAttribute("loggedInUser")).getUserId();
//        Date journeyDate = (Date) session.getAttribute("bookingJourneyDate");
//        int seats = (int) session.getAttribute("bookingSeats");
//
//        Flight flight = flightService.getFlightById(flightId);
//        
//        // Double-check seat availability before confirming
//        int bookedSeats = bookingService.getTotalBookedSeats(flightId, journeyDate);
//        int availableSeats = flight.getCapacity() - bookedSeats;
//        
//        if (seats > availableSeats) {
//            logger.error("Booking failed: Insufficient seats for flight {} on {}. Available: {}, Requested: {}", 
//                        flightId, journeyDate, availableSeats, seats);
//            model.addAttribute("error", "Not enough seats available! Only " + availableSeats + " seats remaining.");
//            model.addAttribute("flight", flight);
//            return "user/bookFlight";
//        }
//
//        // 1️⃣ Create the booking
//        Booking booking = new Booking();
//        booking.setFlightId(flightId);
//        booking.setUserId(userId);
//        booking.setJourneyDate(journeyDate);
//        booking.setSeats(seats);
//        booking.setBookingDate(new Date());
//        booking.setTotalPrice(seats * flight.getPricePerSeat());
//        booking.setStatus("CONFIRMED");
//
//        bookingService.createBooking(booking);
//        logger.info("Booking created with ID: {}", booking.getBookingId());
//
//        // 2️⃣ Generate tickets for each passenger
//        for (int i = 1; i <= seats; i++) {
//            Ticket ticket = new Ticket();
//            ticket.setBookingId(booking.getBookingId());
//            ticket.setJourneyDate(journeyDate);
//            ticket.setPassengerName(params.get("passengerName" + i));
//            ticket.setAge(Integer.parseInt(params.get("passengerAge" + i)));
//            ticket.setGender(params.get("passengerGender" + i));
//            ticket.setSeatNo("S" + i); // simple seat numbering
//            ticket.setStatus("CONFIRMED");
//            ticketService.saveTicket(ticket);
//        }
//        
//        logger.info("{} tickets generated for booking {}", seats, booking.getBookingId());
//
//        // 3️⃣ UPDATE FLIGHT CAPACITY (reduce available seats)
//        int previousCapacity = flight.getCapacity();
//        flight.setCapacity(flight.getCapacity() - seats);
//        flightService.updateFlight(flight);
//        logger.info("Flight {} capacity reduced from {} to {} after booking", flightId, previousCapacity, flight.getCapacity());
//
//        // 4️⃣ Pass booking and flight info to the confirmation page
//        model.addAttribute("booking", booking);
//        model.addAttribute("flight", flight);
//
//        return "user/bookingConfirmation";
//    }
//    
    
    
    @PostMapping("/confirmBooking")
    public String confirmBooking(HttpSession session,
                                 @RequestParam Map<String, String> params,
                                 Model model) {

        Long flightId = (Long) session.getAttribute("bookingFlightId");
        Long userId = ((User) session.getAttribute("loggedInUser")).getUserId();
        Date journeyDate = (Date) session.getAttribute("bookingJourneyDate");
        int seats = (int) session.getAttribute("bookingSeats");

        Flight flight = flightService.getFlightById(flightId);
        
        // Double-check seat availability before confirming
        int bookedSeats = bookingService.getTotalBookedSeats(flightId, journeyDate);
        int availableSeats = flight.getCapacity() - bookedSeats;
        
        if (seats > availableSeats) {
            logger.error("Booking failed: Insufficient seats for flight {} on {}. Available: {}, Requested: {}", 
                        flightId, journeyDate, availableSeats, seats);
            model.addAttribute("error", "Not enough seats available! Only " + availableSeats + " seats remaining.");
            model.addAttribute("flight", flight);
            return "user/bookFlight";
        }

        // 1️⃣ Create the booking
        Booking booking = new Booking();
        booking.setFlightId(flightId);
        booking.setUserId(userId);
        booking.setJourneyDate(journeyDate);
        booking.setSeats(seats);
        booking.setBookingDate(new Date());
        booking.setTotalPrice(seats * flight.getPricePerSeat());
        booking.setStatus("CONFIRMED");

        bookingService.createBooking(booking);
        logger.info("Booking created with ID: {}", booking.getBookingId());

        // 2️⃣ Generate tickets for each passenger
        for (int i = 1; i <= seats; i++) {
            Ticket ticket = new Ticket();
            ticket.setBookingId(booking.getBookingId());
            ticket.setJourneyDate(journeyDate);
            ticket.setPassengerName(params.get("passengerName" + i));
            ticket.setAge(Integer.parseInt(params.get("passengerAge" + i)));
            ticket.setGender(params.get("passengerGender" + i));
            ticket.setSeatNo("S" + i); // simple seat numbering
            ticket.setStatus("CONFIRMED");
            ticketService.saveTicket(ticket);
        }
        
        logger.info("{} tickets generated for booking {}", seats, booking.getBookingId());

        // 3️⃣ UPDATE FLIGHT CAPACITY (reduce available seats)
        int previousCapacity = flight.getCapacity();
        flight.setCapacity(flight.getCapacity() - seats);
        flightService.updateFlight(flight);
        logger.info("Flight {} capacity reduced from {} to {} after booking", flightId, previousCapacity, flight.getCapacity());

        // 4️⃣ Pass booking and flight info to the confirmation page
        model.addAttribute("booking", booking);
        model.addAttribute("flight", flight);

        return "user/bookingConfirmation";
    }
    
    @GetMapping("/addPassenger")
    public String addPassengerForm(HttpSession session, Model model) {
        // Get booking info from session
        Long flightId = (Long) session.getAttribute("bookingFlightId");
        Integer seats = (Integer) session.getAttribute("bookingSeats");
        Date journeyDate = (Date) session.getAttribute("bookingJourneyDate");
        User user = (User) session.getAttribute("loggedInUser");

        if (flightId == null || seats == null || journeyDate == null || user == null) {
            // No booking info in session, redirect to home or flights page
            return "redirect:/";
        }

        Flight flight = flightService.getFlightById(flightId);

        model.addAttribute("flightId", flightId);
        model.addAttribute("seats", seats);
        model.addAttribute("journeyDate", journeyDate);
        model.addAttribute("user", user);
        model.addAttribute("flight", flight);

        return "user/addPassenger"; // JSP for passenger form
    }
    
    @GetMapping("/myBookings")
    public String myBookings(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<Booking> bookings = bookingService.getUserBookings(user.getUserId());
        model.addAttribute("bookings", bookings);
        return "user/myBookings"; // JSP page
    }

//    @PostMapping("/cancelBooking")
//    public String cancelBooking(@RequestParam("bookingId") Long bookingId, HttpSession session, Model model) {
//        User user = (User) session.getAttribute("loggedInUser");
//        if (user == null) {
//            return "redirect:/login";
//        }
//
//        Booking booking = bookingService.getBookingById(bookingId);
//        if (booking != null && booking.getUserId() == user.getUserId() && !"CANCELLED".equalsIgnoreCase(booking.getStatus())) {
//            // Release seats (update flight capacity)
//            Flight flight = flightService.getFlightById(booking.getFlightId());
//            flight.setCapacity(flight.getCapacity() + booking.getSeats());
//            flightService.updateFlight(flight);
//
//            // Update booking status
//            booking.setStatus("CANCELLED");
//            bookingService.updateBooking(booking);
//            
//            // Update all tickets status to CANCELLED
//            ticketService.updateTicketsByBookingId(bookingId, "CANCELLED");
//            
//            logger.info("Booking {} and all associated tickets cancelled by user {}", bookingId, user.getUserId());
//        }
//
//        return "redirect:/user/myBookings";
//    }
    
    
    @PostMapping("/cancelBooking")
    public String cancelBooking(@RequestParam("bookingId") Long bookingId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null && booking.getUserId() == user.getUserId() && !"CANCELLED".equalsIgnoreCase(booking.getStatus())) {
            // Release seats (update flight capacity)
            Flight flight = flightService.getFlightById(booking.getFlightId());
            flight.setCapacity(flight.getCapacity() + booking.getSeats());
            flightService.updateFlight(flight);

            // Update booking status
            booking.setStatus("CANCELLED");
            bookingService.updateBooking(booking);
            
            // 👈 NEW: Update all tickets status to CANCELLED
            ticketService.updateTicketsByBookingId(bookingId, "CANCELLED");
            
            logger.info("Booking {} and all associated tickets cancelled by user {}", bookingId, user.getUserId());
        }

        return "redirect:/user/myBookings";
    }
    @GetMapping("/viewTickets")
    public String viewTickets(@RequestParam("bookingId") Long bookingId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        Booking booking = bookingService.getBookingById(bookingId);
        Flight flight = flightService.getFlightById(booking.getFlightId());
        List<Ticket> tickets = ticketService.getTicketsByBookingId(bookingId);
        
        model.addAttribute("booking", booking);
        model.addAttribute("flight", flight);
        model.addAttribute("tickets", tickets);
        logger.info("Viewing {} tickets for booking {}", tickets.size(), bookingId);
        return "user/viewTickets";
    }
}







