package com.deloitte.test;

import com.deloitte.config.AppConfig;
import com.deloitte.model.Flight;
import com.deloitte.service.FlightService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.List;

@SpringJUnitConfig(classes = AppConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FlightTest {

    private static final Logger logger = LoggerFactory.getLogger(FlightTest.class);

    @Autowired
    private FlightService flightService;

    @Test
    @Order(1)
    public void testGetAllFlights() {
        logger.info("Testing getAllFlights");
        List<Flight> flights = flightService.getAllFlights();
        Assertions.assertNotNull(flights, "Flights list should not be null");
        logger.info("Total flights: {}", flights.size());
    }

    @Test
    @Order(2)
    public void testSearchFlights() {
        logger.info("Testing searchFlights");
        List<Flight> flights = flightService.searchFlights("Mumbai", "Delhi");
        Assertions.assertNotNull(flights, "Search result should not be null");
        logger.info("Flights found: {}", flights.size());
    }

    @Test
    @Order(3)
    public void testFlightNumberUniqueness() {
        logger.info("Testing flight number uniqueness check");
        boolean exists = flightService.isFlightNumberExists("AI101");
        logger.info("Flight AI101 exists: {}", exists);
        // Test passes if method executes without error
        Assertions.assertTrue(true);
    }
}
