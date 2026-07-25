package com.airline.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reservationCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id", nullable = false)
    @NotNull(message = "Flight is required")
    private Flight flight;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", nullable = false)
    @NotNull(message = "Passenger is required")
    private Passenger passenger;

    @NotNull(message = "Booking date is required")
    private LocalDateTime bookingDate;

    @NotNull(message = "Total amount is required")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    // Add baggage directly in reservation
    private Double baggageWeight = 0.0;

    @Enumerated(EnumType.STRING)
    private BaggageType baggageType = BaggageType.CARRY_ON;

    // default constructor
    public Reservation() {
        this.bookingDate = LocalDateTime.now();
        this.status = ReservationStatus.CONFIRMED;
    }

    // constructor with flight, passenger, totalAmount, baggage
    public Reservation(Flight flight, Passenger passenger, BigDecimal totalAmount, Double baggageWeight, BaggageType baggageType) {
        this();
        this.flight = flight;
        this.passenger = passenger;
        this.totalAmount = totalAmount;
        this.baggageWeight = baggageWeight != null ? baggageWeight : 0.0;
        this.baggageType = baggageType != null ? baggageType : BaggageType.CARRY_ON;
        this.reservationCode = generateReservationCode();
    }

    private String generateReservationCode() {
        return "AR" + (System.currentTimeMillis() % 1000000);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReservationCode() { return reservationCode; }
    public void setReservationCode(String reservationCode) { this.reservationCode = reservationCode; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Passenger getPassenger() { return passenger; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    public Double getBaggageWeight() { return baggageWeight; }
    public void setBaggageWeight(Double baggageWeight) { this.baggageWeight = baggageWeight; }

    public BaggageType getBaggageType() { return baggageType; }
    public void setBaggageType(BaggageType baggageType) { this.baggageType = baggageType; }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationCode='" + reservationCode + '\'' +
                ", flight=" + flight +
                ", passenger=" + passenger +
                ", baggageWeight=" + baggageWeight +
                ", baggageType=" + baggageType +
                ", bookingDate=" + bookingDate +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}