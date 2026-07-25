package com.airline.service;

import com.airline.model.Baggage;
import com.airline.model.BaggageType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BaggageService {

    public Baggage calculateBaggageCharge(Double weight, BaggageType requestedType) {
        if (weight == null || weight < 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }

        BaggageType actualType = determineBaggageType(weight, requestedType);
        BigDecimal charge = calculateCharge(weight, actualType);
        String description = generateDescription(weight, actualType, requestedType);

        return new Baggage(weight, actualType, charge, description);
    }

    private BaggageType determineBaggageType(Double weight, BaggageType requestedType) {
        if (requestedType == BaggageType.CARRY_ON && weight > BaggageType.CARRY_ON.getWeightLimit()) {
            return BaggageType.CHECKED;
        }
        if (requestedType == BaggageType.CHECKED && weight > BaggageType.CHECKED.getWeightLimit()) {
            return BaggageType.OVERWEIGHT;
        }
        return requestedType;
    }

    private BigDecimal calculateCharge(Double weight, BaggageType type) {
        BigDecimal charge = BigDecimal.ZERO;

        switch (type) {
            case CARRY_ON:
                charge = BigDecimal.ZERO;
                break;
            case CHECKED:
                charge = new BigDecimal("400.00"); // ₹400 for checked baggage
                break;
            case OVERWEIGHT:
                charge = new BigDecimal("400.00");
                double excessWeight = weight - BaggageType.CHECKED.getWeightLimit();
                BigDecimal overweightCharge = new BigDecimal(excessWeight * 300); // ₹300 per excess kg
                charge = charge.add(overweightCharge);
                break;
        }

        return charge.setScale(2, RoundingMode.HALF_UP);
    }

    private String generateDescription(Double weight, BaggageType actualType, BaggageType requestedType) {
        StringBuilder description = new StringBuilder();
        description.append(String.format("Baggage: %.1f kg - %s", weight, actualType.getDisplayName()));

        if (actualType != requestedType) {
            description.append(String.format(" (upgraded from %s due to weight)", requestedType.getDisplayName()));
        }

        if (actualType == BaggageType.OVERWEIGHT) {
            double excessWeight = weight - BaggageType.CHECKED.getWeightLimit();
            description.append(String.format(" - Excess: %.1f kg", excessWeight));
        }

        return description.toString();
    }

    public void printBaggageInfo() {
        System.out.println("\n=== BAGGAGE POLICY ===");
        System.out.println("Carry-on: Up to 7 kg - FREE");
        System.out.println("Checked: Up to 23 kg - ₹400.00");
        System.out.println("Overweight: Above 23 kg - ₹400.00 + ₹300 per excess kg");
        System.out.println("========================\n");
    }
}