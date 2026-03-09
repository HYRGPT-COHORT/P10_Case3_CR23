package com.deloitte.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.deloitte.mapper.FlightRowMapper;
import com.deloitte.model.Flight;

@Repository
public class FlightDaoImpl implements FlightDao {

    private static final Logger logger = LoggerFactory.getLogger(FlightDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

  
    public void saveFlight(Flight flight) {
    	logger.info("Saving flight: {}", flight.getFlightNumber());
        String sql = "INSERT INTO flights (flight_number, source, destination, departure_time, arrival_time, capacity, price_per_seat) VALUES (?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql,
                flight.getFlightNumber(),
                flight.getSource(),
                flight.getDestination(),
               flight.getDepartureTime(),
            flight.getArrivalTime(),
                flight.getCapacity(),
                flight.getPricePerSeat());
    }

    public List<Flight> getAllFlights() {

        String sql = "select * from flights";
        return jdbcTemplate.query(sql, new FlightRowMapper());
    }

    public Flight getFlightById(long flightId) {

        String sql = "select * from flights where flight_id=?";

        List<Flight> list = jdbcTemplate.query(sql, new Object[]{flightId}, new FlightRowMapper());

        return list.isEmpty() ? null : list.get(0);
    }

    public void updateFlight(Flight flight) {

        String sql = "UPDATE flights SET flight_number=?, source=?, destination=?, departure_time=?, arrival_time=?, capacity=?, price_per_seat=? WHERE flight_id=?";

        jdbcTemplate.update(sql,
                flight.getFlightNumber(),
                flight.getSource(),
                flight.getDestination(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getCapacity(),
                flight.getPricePerSeat(),
                flight.getFlightId());
    }

    public void deleteFlight(long flightId) {

        String sql = "DELETE FROM flights WHERE flight_id=?";

        jdbcTemplate.update(sql, flightId);
    }

    public List<Flight> searchFlights(String source, String destination) {

        String sql = "SELECT * FROM flights WHERE lower(source)=lower(?) AND lower(destination)=lower(?)";

        return jdbcTemplate.query(sql, new Object[]{source, destination}, new FlightRowMapper());
    }
    
    @Override
    public boolean isFlightNumberExists(String flightNumber) {
        String sql = "SELECT COUNT(*) FROM flights WHERE flight_number = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{flightNumber}, Integer.class);
        return count != null && count > 0;
    }
    
   
}