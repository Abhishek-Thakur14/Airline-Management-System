package com.airline.service;

import com.airline.model.Flight;
import com.airline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> getAvailableFlights() {
        return flightRepository.findAllAvailableFlights();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Optional<Flight> getFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }

    public List<Flight> searchFlights(String origin, String destination, LocalDateTime departureDate) {
        if (departureDate != null) {
            return flightRepository.findAvailableFlights(origin, destination, departureDate);
        } else {
            return flightRepository.findByOriginAndDestination(origin, destination);
        }
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public boolean reserveSeat(Long flightId) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            if (flight.getAvailableSeats() > 0) {
                flight.setAvailableSeats(flight.getAvailableSeats() - 1);
                flightRepository.save(flight);
                return true;
            }
        }
        return false;
    }

    public void releaseSeat(Long flightId) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            if (flight.getAvailableSeats() < flight.getTotalSeats()) {
                flight.setAvailableSeats(flight.getAvailableSeats() + 1);
                flightRepository.save(flight);
            }
        }
    }
}