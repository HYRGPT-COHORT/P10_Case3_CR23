package com.deloitte.test;

import com.deloitte.config.AppConfig;
import com.deloitte.model.Booking;
import com.deloitte.service.BookingService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(classes = AppConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingCancellationTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingCancellationTest.class);

    @Autowired
    private BookingService bookingService;

    @Test
    @Order(1)
    public void testGetUserBookings() {
        logger.info("Testing getUserBookings");
        List<Booking> bookings = bookingService.getUserBookings(1L); // Assuming user ID 1 exists
        Assertions.assertNotNull(bookings, "Bookings should not be null");
        logger.info("User bookings count: {}", bookings.size());
    }

    @Test
    @Order(2)
    public void testBookingStatusUpdate() {
        logger.info("Testing booking status update");
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setStatus("CANCELLED");
        
        // This tests if the method executes without exceptions
        try {
            bookingService.updateBooking(booking);
            logger.info("Booking status updated successfully");
            Assertions.assertTrue(true);
        } catch (Exception e) {
            logger.error("Failed to update booking", e);
            Assertions.fail("Booking update failed");
        }
    }
}
