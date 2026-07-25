package com.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirlineReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineReservationSystemApplication.class, args);
        System.out.println("=".repeat(50));
        System.out.println("=== Airline Reservation System Started ===");
        System.out.println("=".repeat(50));
        System.out.println("Access the system at: http://localhost:0");
        System.out.println("\nAvailable endpoints:");
        System.out.println("- GET /api/flights - View all flights");
        System.out.println("- POST /api/reservations - Make a reservation");
        System.out.println("- GET /api/reservations/{id} - View reservation details");
        System.out.println("- POST /api/baggage/calculate - Calculate baggage charges");
        System.out.println("=".repeat(50));
    }
}