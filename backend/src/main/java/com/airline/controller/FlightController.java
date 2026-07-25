package com.airline.controller;

import com.airline.model.Flight;
import com.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
// ✅ Allow requests from your frontend
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> fetchAllFlights() {
        List<Flight> flightList = flightService.getAvailableFlights();
        return ResponseEntity.ok(flightList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> fetchFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam(required = false) String departureDate) {

        LocalDateTime departureTime = null;
        if (departureDate != null && !departureDate.isBlank()) {
            departureTime = LocalDateTime.parse(departureDate);
        }

        List<Flight> result = flightService.searchFlights(origin, destination, departureTime);
        return ResponseEntity.ok(result);
    }
}