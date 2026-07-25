package com.airline.service;

import com.airline.model.*;
import com.airline.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private BaggageService baggageService;

    @Transactional
    public Reservation createReservation(Long flightId, Passenger passenger,
                                         Double baggageWeight, BaggageType baggageType) {
        // Fetch flight
        Flight flight = flightService.getFlightById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        // Reserve seat
        if (!flightService.reserveSeat(flightId)) {
            throw new RuntimeException("No seats available");
        }

        // Start with flight base price
        BigDecimal totalAmount = flight.getPrice();

        // Calculate baggage charge if baggageWeight > 0
        if (baggageWeight != null && baggageWeight > 0) {
            Baggage baggage = baggageService.calculateBaggageCharge(
                    baggageWeight,
                    baggageType != null ? baggageType : BaggageType.CARRY_ON
            );
            totalAmount = totalAmount.add(baggage.getCharge());
        }

        // Create reservation including baggage info
        Reservation reservation = new Reservation(flight, passenger, totalAmount,
                baggageWeight != null ? baggageWeight : 0.0,
                baggageType != null ? baggageType : BaggageType.CARRY_ON
        );

        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Optional<Reservation> getReservationByCode(String code) {
        return reservationRepository.findByReservationCode(code);
    }

    public List<Reservation> getReservationsByEmail(String email) {
        return reservationRepository.findByPassengerEmail(email);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public boolean cancelReservation(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
            flightService.releaseSeat(reservation.getFlight().getId());
            return true;
        }).orElse(false);
    }
}