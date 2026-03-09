package com.deloitte.test;

import com.deloitte.model.Booking;
import com.deloitte.model.Flight;
import com.deloitte.model.User;
import com.deloitte.service.BookingService;
import com.deloitte.service.FlightService;
import com.deloitte.service.UserService;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.deloitte.config.AppConfig;

import java.util.Date;
import java.util.List;

@SpringJUnitConfig(classes = AppConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    private User testUser;
    private Flight testFlight;

    @BeforeEach
    public void setup() {
        // fetch user from DB
        testUser = userService.validateUser("user1@gmail.com", "password", "USER");
        Assertions.assertNotNull(testUser, "Test user must exist in DB");

        // fetch any flight
        List<Flight> flights = flightService.getAllFlights();
        Assertions.assertFalse(flights.isEmpty(), "At least one flight must exist");
        testFlight = flights.get(0);

        logger.info("Setup complete: User {} and Flight {}", testUser.getEmail(), testFlight.getFlightNumber());
    }

    @Test
    @Order(1)
    public void testCreateBooking() {
        logger.info("Creating booking for test user");

        Booking booking = new Booking();
        booking.setUserId(testUser.getUserId());
        booking.setFlightId(testFlight.getFlightId());
        booking.setBookingDate(new Date());
        booking.setJourneyDate(new Date());
        booking.setSeats(1);
        booking.setTotalPrice(testFlight.getPricePerSeat());
        booking.setStatus("CONFIRMED");

        bookingService.createBooking(booking);

        Assertions.assertNotNull(booking.getBookingId(), "Booking ID should be generated");
        logger.info("Booking created successfully with ID: {}", booking.getBookingId());
    }
}