package com.deloitte.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deloitte.model.Flight;
import com.deloitte.model.User;
import com.deloitte.service.FlightService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private FlightService flightService;

    // this mwthod will open admin dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {   //model--
    	//httpsession -
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            return "redirect:/login/admin";
        }

        model.addAttribute("username", loggedInUser.getEmail());
        return "admin/dashboard";
    }

    
    //make admin to add flights
    @GetMapping("/addFlight")
    public String addFlightPage(Model model) {
        model.addAttribute("flight", new Flight());
        return "admin/addFlight"; // render the jsp page tgrough viewresiolver
    }
//    
    
   
//method to save flight
    @PostMapping("/saveFlight")
    public String saveFlight(@Valid @ModelAttribute Flight flight, BindingResult result, Model model) {
        
        // VALIDATION 1: Check @Valid annotations (@NotBlank, @Min, @Positive)
        if (result.hasErrors()) {
            logger.warn("Flight validation failed: {}", result.getFieldErrors());
            return "admin/addFlight";
        }
        
        // VALIDATION 2: Source and destination cannot be the same
        if (flight.getSource() != null && flight.getDestination() != null &&
            flight.getSource().trim().equalsIgnoreCase(flight.getDestination().trim())) {
            logger.warn("Invalid flight: Source and destination are the same: {}", flight.getSource());
            model.addAttribute("error", "❌ Error: Source and destination cannot be the same!");
            model.addAttribute("flight", flight);
            return "admin/addFlight";
        }
        
        // VALIDATION 3: Check if flight number already exists
        if (flightService.isFlightNumberExists(flight.getFlightNumber())) {
            logger.warn("Duplicate flight number: {}", flight.getFlightNumber());
            model.addAttribute("error", "❌ Error: Flight number already exists. Please use a unique flight number.");
            model.addAttribute("flight", flight);
            return "admin/addFlight";
        }
        
        // VALIDATION 4: Ensure source and destination are not empty after trim
        if (flight.getSource() == null || flight.getSource().trim().isEmpty() ||
            flight.getDestination() == null || flight.getDestination().trim().isEmpty()) {
            logger.warn("Source or destination is empty");
            model.addAttribute("error", "❌ Error: Source and destination cannot be empty!");
            model.addAttribute("flight", flight);
            return "admin/addFlight";
        }
        
        // VALIDATION 5: Ensure capacity is positive
        if (flight.getCapacity() <= 0) {
            logger.warn("Invalid capacity: {}", flight.getCapacity());
            model.addAttribute("error", "❌ Error: Capacity must be greater than 0!");
            model.addAttribute("flight", flight);
            return "admin/addFlight";
        }
        
        // VALIDATION 6: Ensure price per seat is positive
        if (flight.getPricePerSeat() <= 0) {
            logger.warn("Invalid price: {}", flight.getPricePerSeat());
            model.addAttribute("error", "❌ Error: Price per seat must be greater than 0!");
            model.addAttribute("flight", flight);
            return "admin/addFlight";
        }
        
        // All validations passed - Save flight
        try {
            flightService.addFlight(flight);
            logger.info("✅ Flight added successfully: {} ({} → {})", flight.getFlightNumber(), flight.getSource(), flight.getDestination());
            model.addAttribute("success", "✅ Flight added successfully!");
        } catch (Exception e) {
            logger.error("Error adding flight: {}", e.getMessage(), e);
            model.addAttribute("error", "❌ Error: Something went wrong while adding the flight. Please try again.");
            model.addAttribute("flight", flight);
            return "admin/addFlight";
        }
        
        return "redirect:/admin/flights";
    }


    
}
