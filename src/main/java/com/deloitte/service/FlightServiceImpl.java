package com.deloitte.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.dao.FlightDao;
import com.deloitte.model.Flight;

@Service
public class FlightServiceImpl implements FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

    @Autowired
    private FlightDao flightDao;

    @Override
    public void addFlight(Flight flight) {
      	logger.info("Adding new flight: {}", flight.getFlightNumber());
        flightDao.saveFlight(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
    	
        return flightDao.getAllFlights();
    }

    @Override
    public Flight getFlightById(long flightId) {
        return flightDao.getFlightById(flightId);
    }

    @Override
    public void updateFlight(Flight flight) {
        flightDao.updateFlight(flight);
    }

    @Override
    public void deleteFlight(long flightId) {
        flightDao.deleteFlight(flightId);
    }

    @Override
    public List<Flight> searchFlights(String source, String destination) {
        return flightDao.searchFlights(source, destination);
    }
    
    @Override
    public boolean isFlightNumberExists(String flightNumber) {
        return flightDao.isFlightNumberExists(flightNumber);
    }
}