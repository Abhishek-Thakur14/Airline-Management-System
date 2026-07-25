package com.airline.controller;

import com.airline.model.Passenger;
import com.airline.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
@CrossOrigin(origins = "http://localhost:3000")
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepo;

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger p) {
        Passenger newPassenger = passengerRepo.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPassenger);
    }
}