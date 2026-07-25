package com.airline.repository;

import com.airline.model.Reservation;
import com.airline.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByReservationCode(String reservationCode);

    List<Reservation> findByPassengerEmail(String email);

    List<Reservation> findByStatus(ReservationStatus status);

    List<Reservation> findByFlightId(Long flightId);
}