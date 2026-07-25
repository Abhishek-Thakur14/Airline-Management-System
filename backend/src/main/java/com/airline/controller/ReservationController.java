package com.airline.controller;

import com.airline.dto.ReservationRequest;
import com.airline.model.Reservation;
import com.airline.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> bookReservation(@Valid @RequestBody ReservationRequest req) {
        try {
            Reservation res = reservationService.createReservation(
                    req.getFlightId(),
                    req.getPassenger(),
                    req.getBaggageWeight(),
                    req.getBaggageType()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Could not create reservation: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> fetchById(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Reservation> fetchByCode(@PathVariable String code) {
        return reservationService.getReservationByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> fetchAll() {
        List<Reservation> list = reservationService.getAllReservations();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/passenger/{email}")
    public ResponseEntity<List<Reservation>> fetchByPassenger(@PathVariable String email) {
        List<Reservation> list = reservationService.getReservationsByEmail(email);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        boolean done = reservationService.cancelReservation(id);
        if (done) {
            return ResponseEntity.ok("Reservation cancelled");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}