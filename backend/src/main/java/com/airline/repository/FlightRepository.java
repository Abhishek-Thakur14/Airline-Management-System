package com.airline.repository;

import com.airline.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFlightNumber(String flightNumber);

    List<Flight> findByOriginAndDestination(String origin, String destination);

    @Query("SELECT f FROM Flight f WHERE f.origin = :origin AND f.destination = :destination " +
           "AND f.departureTime >= :departureDate AND f.availableSeats > 0")
    List<Flight> findAvailableFlights(@Param("origin") String origin,
                                      @Param("destination") String destination,
                                      @Param("departureDate") LocalDateTime departureDate);

    @Query("SELECT f FROM Flight f WHERE f.availableSeats > 0 ORDER BY f.departureTime")
    List<Flight> findAllAvailableFlights();
}