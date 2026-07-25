package com.airline.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "baggage")
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Weight is required")
    @PositiveOrZero(message = "Weight must be zero or more")
    private Double weight;  // in kilograms

    @NotNull(message = "Baggage type is required")
    @Enumerated(EnumType.STRING)
    private BaggageType type;

    @NotNull(message = "Charge is required")
    @PositiveOrZero(message = "Charge must be zero or more")
    private BigDecimal charge;

    private String description;

    public Baggage() {
    }

    public Baggage(Double weight, BaggageType type, BigDecimal charge, String description) {
        this.weight = weight;
        this.type = type;
        this.charge = charge;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "id=" + id +
                ", weight=" + weight +
                ", type=" + type +
                ", charge=" + charge +
                ", description='" + description + '\'' +
                '}';
    }
}