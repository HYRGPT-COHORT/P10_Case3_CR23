package com.deloitte.service;

import java.util.List;
import com.deloitte.model.Flight;

public interface FlightService {

    void addFlight(Flight flight);

    List<Flight> getAllFlights();

    Flight getFlightById(long flightId);

    void updateFlight(Flight flight);

    void deleteFlight(long flightId);

    List<Flight> searchFlights(String source, String destination);
    
    boolean isFlightNumberExists(String flightNumber);
}