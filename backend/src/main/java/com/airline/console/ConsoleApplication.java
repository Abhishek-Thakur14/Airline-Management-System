package com.airline.console;

import com.airline.model.*;
import com.airline.service.BaggageService;
import com.airline.service.FlightService;
import com.airline.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleApplication implements CommandLineRunner {

    @Autowired
    private FlightService flightService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BaggageService baggageService;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(2000);
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        ✈ AIRLINE RESERVATION SYSTEM (INDIA) ✈");
        System.out.println("=".repeat(60));

        while (true) {
            showMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> viewAllFlights();
                case 2 -> searchFlights();
                case 3 -> makeReservation();
                case 4 -> viewReservation();
                case 5 -> calculateBaggageCharge();
                case 6 -> viewAllReservations();
                case 7 -> cancelReservation();
                case 8 -> baggageService.printBaggageInfo();
                case 0 -> {
                    System.out.println("Thank you for booking with us. Safe travels!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void showMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("                MAIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("1. View All Available Flights");
        System.out.println("2. Search Flights");
        System.out.println("3. Make Reservation");
        System.out.println("4. View Reservation");
        System.out.println("5. Calculate Baggage Charge");
        System.out.println("6. View All Reservations");
        System.out.println("7. Cancel Reservation");
        System.out.println("8. View Baggage Policy");
        System.out.println("0. Exit");
        System.out.println("=".repeat(40));
    }

    private void viewAllFlights() {
        System.out.println("\n" + "=".repeat(90));
        System.out.println("                           AVAILABLE INDIAN FLIGHTS");
        System.out.println("=".repeat(90));

        List<Flight> flights = flightService.getAvailableFlights();
        if (flights.isEmpty()) {
            System.out.println("No flights available at the moment.");
            return;
        }

        System.out.printf("%-8s %-15s %-15s %-15s %-20s %-10s %-8s%n",
                "ID", "Flight No", "Origin", "Destination", "Departure", "Price(₹)", "Seats");
        System.out.println("-".repeat(90));

        for (Flight flight : flights) {
            System.out.printf("%-8d %-15s %-15s %-15s %-20s ₹%-9.2f %-8d%n",
                    flight.getId(),
                    flight.getFlightNumber(),
                    flight.getOrigin(),
                    flight.getDestination(),
                    flight.getDepartureTime().toString().replace("T", " "),
                    flight.getPrice(),
                    flight.getAvailableSeats());
        }
    }

    private void searchFlights() {
        System.out.println("\n=== SEARCH FLIGHTS ===");
        System.out.print("Enter origin city: ");
        String origin = scanner.nextLine().trim();
        System.out.print("Enter destination city: ");
        String destination = scanner.nextLine().trim();

        List<Flight> flights = flightService.searchFlights(origin, destination, null);
        if (flights.isEmpty()) {
            System.out.println("No flights found for this route.");
            return;
        }

        System.out.println("\n=== SEARCH RESULTS ===");
        System.out.printf("%-8s %-15s %-20s %-20s %-10s %-8s%n",
                "ID", "Flight No", "Departure", "Arrival", "Price(₹)", "Seats");
        System.out.println("-".repeat(80));

        for (Flight flight : flights) {
            System.out.printf("%-8d %-15s %-20s %-20s ₹%-9.2f %-8d%n",
                    flight.getId(),
                    flight.getFlightNumber(),
                    flight.getDepartureTime().toString().replace("T", " "),
                    flight.getArrivalTime().toString().replace("T", " "),
                    flight.getPrice(),
                    flight.getAvailableSeats());
        }
    }

    private void makeReservation() {
        System.out.println("\n=== MAKE RESERVATION ===");

        // show available flights
        viewAllFlights();

        Long flightId = getLongInput("Enter Flight ID: ");
        Optional<Flight> flightOpt = flightService.getFlightById(flightId);

        if (!flightOpt.isPresent()) {
            System.out.println("Flight not found!");
            return;
        }

        Flight flight = flightOpt.get();
        System.out.println("Selected Flight: " + flight.getFlightNumber() +
                " from " + flight.getOrigin() + " to " + flight.getDestination());

        // Get passenger details
        System.out.println("\n--- PASSENGER INFORMATION ---");
        System.out.print("First Name (e.g., Vipul): ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Last Name (e.g., Sharma): ");
        String lastName = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Passport / Govt. ID Number: ");
        String passport = scanner.nextLine().trim();

        Passenger passenger = new Passenger(firstName, lastName, email, phone, passport);

        // Get baggage details
        System.out.println("\n--- BAGGAGE INFORMATION ---");
        baggageService.printBaggageInfo();

        System.out.print("Do you have baggage? (y/n): ");
        String hasBaggage = scanner.nextLine().trim().toLowerCase();

        Double baggageWeight = 0.0;
        BaggageType baggageType = BaggageType.CARRY_ON;

        if (hasBaggage.equals("y") || hasBaggage.equals("yes")) {
            baggageWeight = getDoubleInput("Enter baggage weight (kg): ");

            System.out.println("Baggage Types:");
            System.out.println("1. Carry-on (up to 7 kg)");
            System.out.println("2. Checked (up to 23 kg)");
            int typeChoice = getIntInput("Select baggage type (1-2): ");

            baggageType = (typeChoice == 2) ? BaggageType.CHECKED : BaggageType.CARRY_ON;
        }

        try {
            // Calculate total price dynamically
            BigDecimal totalAmount = flight.getPrice();
            Baggage baggage = null;
            if (baggageWeight > 0) {
                baggage = baggageService.calculateBaggageCharge(baggageWeight, baggageType);
                totalAmount = totalAmount.add(baggage.getCharge());
            }

            Reservation reservation = reservationService.createReservation(
                    flightId, passenger, baggageWeight, baggageType);

            // Show booking confirmation with live pricing
            System.out.println("\n" + "=".repeat(50));
            System.out.println("         ✅ RESERVATION CONFIRMED ✅");
            System.out.println("=".repeat(50));
            System.out.println("Reservation Code: " + reservation.getReservationCode());
            System.out.println("Passenger: " + passenger.getFullName());
            System.out.println("Flight: " + flight.getFlightNumber());
            System.out.println("Route: " + flight.getOrigin() + " → " + flight.getDestination());
            System.out.println("Departure: " + flight.getDepartureTime().toString().replace("T", " "));

            if (baggageWeight > 0) {
                System.out.println("Baggage Weight: " + baggageWeight + " kg");
                System.out.println("Baggage Type: " + baggageType.getDisplayName());
                System.out.println("Baggage Charge: ₹" + baggage.getCharge());
            } else {
                System.out.println("Baggage: Carry-on (0 kg) - FREE");
            }

            System.out.println("Total Amount: ₹" + totalAmount);
            System.out.println("=".repeat(50));

        } catch (Exception e) {
            System.out.println("Error creating reservation: " + e.getMessage());
        }
    }

    private void viewReservation() {
        System.out.println("\n=== VIEW RESERVATION ===");
        System.out.print("Enter Reservation Code: ");
        String code = scanner.nextLine().trim();

        Optional<Reservation> reservationOpt = reservationService.getReservationByCode(code);
        if (!reservationOpt.isPresent()) {
            System.out.println("Reservation not found!");
            return;
        }

        Reservation reservation = reservationOpt.get();
        Flight flight = reservation.getFlight();
        Passenger passenger = reservation.getPassenger();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("        RESERVATION DETAILS");
        System.out.println("=".repeat(50));
        System.out.println("Reservation Code: " + reservation.getReservationCode());
        System.out.println("Status: " + reservation.getStatus());
        System.out.println("Booking Date: " + reservation.getBookingDate().toString().replace("T", " "));
        System.out.println();
        System.out.println("Passenger: " + passenger.getFullName());
        System.out.println("Email: " + passenger.getEmail());
        System.out.println("Phone: " + passenger.getPhoneNumber());
        System.out.println();
        System.out.println("Flight: " + flight.getFlightNumber());
        System.out.println("Route: " + flight.getOrigin() + " → " + flight.getDestination());
        System.out.println("Departure: " + flight.getDepartureTime().toString().replace("T", " "));
        System.out.println("Arrival: " + flight.getArrivalTime().toString().replace("T", " "));
        System.out.println();
        System.out.println("Baggage Weight: " + reservation.getBaggageWeight() + " kg");
        System.out.println("Baggage Type: " + reservation.getBaggageType());
        System.out.println();
        System.out.println("Total Amount: ₹" + reservation.getTotalAmount());
        System.out.println("=".repeat(50));
    }

    private void calculateBaggageCharge() {
        System.out.println("\n=== BAGGAGE CHARGE CALCULATOR ===");
        baggageService.printBaggageInfo();

        Double weight = getDoubleInput("Enter baggage weight (kg): ");
        System.out.println("Baggage Types: 1. Carry-on 2. Checked");
        int typeChoice = getIntInput("Select intended baggage type (1-2): ");
        BaggageType type = (typeChoice == 2) ? BaggageType.CHECKED : BaggageType.CARRY_ON;

        try {
            Baggage baggage = baggageService.calculateBaggageCharge(weight, type);
            System.out.println("\n=== BAGGAGE CALCULATION RESULT ===");
            System.out.println("Weight: " + weight + " kg");
            System.out.println("Requested Type: " + type.getDisplayName());
            System.out.println("Actual Type: " + baggage.getType().getDisplayName());
            System.out.println("Description: " + baggage.getDescription());
            System.out.println("Charge: ₹" + baggage.getCharge());
            System.out.println("=".repeat(35));
        } catch (Exception e) {
            System.out.println("Error calculating baggage charge: " + e.getMessage());
        }
    }

    private void viewAllReservations() {
        System.out.println("\n=== ALL RESERVATIONS ===");
        List<Reservation> reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.printf("%-8s %-12s %-20s %-15s %-15s %-10s %-10s%n",
                "ID", "Code", "Passenger", "Flight", "Route", "Amount", "Status");
        System.out.println("-".repeat(90));

        for (Reservation reservation : reservations) {
            Flight flight = reservation.getFlight();
            System.out.printf("%-8d %-12s %-20s %-15s %-15s ₹%-9.2f %-10s%n",
                    reservation.getId(),
                    reservation.getReservationCode(),
                    reservation.getPassenger().getFullName(),
                    flight.getFlightNumber(),
                    flight.getOrigin() + "→" + flight.getDestination(),
                    reservation.getTotalAmount(),
                    reservation.getStatus());
        }
    }

    private void cancelReservation() {
        System.out.println("\n=== CANCEL RESERVATION ===");
        Long reservationId = getLongInput("Enter Reservation ID: ");
        boolean cancelled = reservationService.cancelReservation(reservationId);
        if (cancelled) {
            System.out.println("Reservation cancelled successfully!");
        } else {
            System.out.println("Reservation not found or could not be cancelled.");
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private Long getLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private Double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}