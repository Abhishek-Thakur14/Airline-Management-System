package com.airline.dto;

import com.airline.model.BaggageType;
import com.airline.model.Passenger;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ReservationRequest {

    @NotNull(message = "Flight ID is required")
    private Long flightId;

    @Valid
    @NotNull(message = "Passenger details are required")
    private Passenger passenger;

    @PositiveOrZero(message = "Baggage weight must be zero or more")
    private Double baggageWeight;

    private BaggageType baggageType = BaggageType.CARRY_ON;

    public ReservationRequest() {}

    public ReservationRequest(Long flightId, Passenger passenger, Double baggageWeight, BaggageType baggageType) {
        this.flightId = flightId;
        this.passenger = passenger;
        this.baggageWeight = baggageWeight;
        this.baggageType = baggageType;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Double getBaggageWeight() {
        return baggageWeight;
    }

    public void setBaggageWeight(Double baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    public BaggageType getBaggageType() {
        return baggageType;
    }

    public void setBaggageType(BaggageType baggageType) {
        this.baggageType = baggageType;
    }
}