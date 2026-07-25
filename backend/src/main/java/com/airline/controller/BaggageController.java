package com.airline.controller;

import com.airline.dto.BaggageCalculationRequest;
import com.airline.model.Baggage;
import com.airline.service.BaggageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/baggage")
public class BaggageController {

    @Autowired
    private BaggageService baggageService;

    // API endpoint to calculate baggage charge
    @PostMapping("/calculate")
    public ResponseEntity<?> calculateBaggageCharge(@Valid @RequestBody BaggageCalculationRequest request) {
        try {
            Baggage baggage = baggageService.calculateBaggageCharge(
                    request.getWeight(),
                    request.getType()
            );
            return ResponseEntity.ok(baggage);
        } catch (Exception e) {
            // Send a clear message instead of a generic error
            return ResponseEntity.badRequest()
                    .body("Unable to calculate baggage charge. Reason: " + e.getMessage());
        }
    }
}