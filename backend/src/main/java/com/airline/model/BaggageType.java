package com.airline.model;

public enum BaggageType {
    CARRY_ON("Carry-on", 7.0, 0.0),
    CHECKED("Checked", 23.0, 25.0),
    OVERWEIGHT("Overweight", Double.MAX_VALUE, 15.0);

    private final String displayName;
    private final Double weightLimit;   // in kg
    private final Double chargePerKg;   // extra charge per kg

    BaggageType(String displayName, Double weightLimit, Double chargePerKg) {
        this.displayName = displayName;
        this.weightLimit = weightLimit;
        this.chargePerKg = chargePerKg;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public Double getChargePerKg() {
        return chargePerKg;
    }
}