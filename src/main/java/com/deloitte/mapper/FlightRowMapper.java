package com.deloitte.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.deloitte.model.Flight;

public  class FlightRowMapper implements RowMapper<Flight> {

        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {

            Flight flight = new Flight();

            flight.setFlightId(rs.getLong("flight_id"));
            flight.setFlightNumber(rs.getString("flight_number"));
            flight.setSource(rs.getString("source"));
            flight.setDestination(rs.getString("destination"));
            
            // Read time as string (format: HH:mm:ss or HH:mm)
            String depTime = rs.getString("departure_time");
            String arrTime = rs.getString("arrival_time");
            
            // Store in HH:mm format (remove seconds if present)
            flight.setDepartureTime(depTime != null && depTime.length() > 5 ? depTime.substring(0, 5) : depTime);
            flight.setArrivalTime(arrTime != null && arrTime.length() > 5 ? arrTime.substring(0, 5) : arrTime);
            
            flight.setCapacity(rs.getInt("capacity"));
            flight.setPricePerSeat(rs.getDouble("price_per_seat"));

            return flight;
        }

		
    }
