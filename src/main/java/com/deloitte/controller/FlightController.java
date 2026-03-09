package com.deloitte.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.deloitte.model.Flight;
import com.deloitte.model.Booking;
import com.deloitte.model.User;
import com.deloitte.dto.FlightJourneyStats;

import com.deloitte.service.FlightService;
import com.deloitte.service.BookingService;
import com.deloitte.service.UserService;

@Controller
public class FlightController {

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/flights")
    public String viewFlights(Model model) {

        List<Flight> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);
        logger.info("Displaying {} flights", flights.size());
        return "flightlist";
    }

    @GetMapping("/admin/flights")
    public String viewAdminFlights(@RequestParam(value = "tab", defaultValue = "flights") String tab, Model model) {
        
        if ("upcoming".equals(tab)) {
            List<FlightJourneyStats> journeyStats = bookingService.getUpcomingJourneyStats();
            model.addAttribute("journeyStats", journeyStats);
            logger.info("Admin viewing {} upcoming journey dates", journeyStats.size());
        } else if ("completed".equals(tab)) {
            List<FlightJourneyStats> journeyStats = bookingService.getCompletedJourneyStats();
            model.addAttribute("journeyStats", journeyStats);
            logger.info("Admin viewing {} completed journey dates", journeyStats.size());
        } else {
            // Default: Show all flights (master list)
            List<Flight> flights = flightService.getAllFlights();
            model.addAttribute("flights", flights);
            logger.info("Admin viewing {} flights in master list", flights.size());
        }
        
        model.addAttribute("activeTab", tab);
        return "admin/viewFlights";
    }

    @GetMapping("/searchFlights")//when user click seach flights it will hit here
    public String searchFlights(@RequestParam String source,
                                @RequestParam String destination,
                                Model model) {
        
        // VALIDATION: Source and destination cannot be the same
        if (source != null && destination != null && 
            source.trim().equalsIgnoreCase(destination.trim())) {
            logger.warn("Invalid search attempt: Source and destination are the same - {}", source);
            
            
        }
        
        // Additional validation: Ensure source and destination are not empty
        if (source == null || source.trim().isEmpty() || 
            destination == null || destination.trim().isEmpty()) {
            logger.warn("Invalid search attempt: Source or destination is empty");
            
        }
        
        logger.info("Searching flights - Source: {}, Destination: {}", source, destination);

        List<Flight> flights = flightService.searchFlights(source, destination);

        logger.info("Found {} flights for route {} → {}", flights.size(), source, destination);
        model.addAttribute("flights", flights);
        
        if (flights.isEmpty()) {
            model.addAttribute("info", "ℹ️ No flights found for this route. Please try a different route.");
        }

        return "flightlist";
    }
}






