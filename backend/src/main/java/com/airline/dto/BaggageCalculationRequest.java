package com.airline.dto;

import com.airline.model.BaggageType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class BaggageCalculationRequest {

    @NotNull(message = "Weight is required")
    @PositiveOrZero(message = "Weight must be zero or more")
    private Double weight;

    @NotNull(message = "Baggage type is required")
    private BaggageType type;

    public BaggageCalculationRequest() {}

    public BaggageCalculationRequest(Double weight, BaggageType type) {
        this.weight = weight;
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public BaggageType getType() {
        return type;
    }

    public void setType(BaggageType type) {
        this.type = type;
    }
}