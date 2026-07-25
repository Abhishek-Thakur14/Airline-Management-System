package com.airline.config;

import com.airline.model.Flight;
import com.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private FlightService flightService;

    @Override
    public void run(String... args) throws Exception {
        // Load some sample flights only if DB is empty
        if (flightService.getAllFlights().isEmpty()) {
            initializeSampleFlights();
            System.out.println("Sample Indian flights initialized!");
        }
    }

    private void initializeSampleFlights() {
        LocalDateTime now = LocalDateTime.now();
    
        // Flight 1: Delhi to Mumbai
        Flight flight1 = new Flight(
                "AI101",
                "Delhi",
                "Mumbai",
                now.plusDays(1).withHour(8).withMinute(0),
                now.plusDays(1).withHour(10).withMinute(15),
                new BigDecimal("3999.99"),
                180
        );
    
        // Flight 2: Mumbai to Bangalore
        Flight flight2 = new Flight(
                "6E202",
                "Mumbai",
                "Bangalore",
                now.plusDays(2).withHour(14).withMinute(15),
                now.plusDays(2).withHour(16).withMinute(30),
                new BigDecimal("2999.99"),
                150
        );
    
        // Flight 3: Bangalore to Kolkata
        Flight flight3 = new Flight(
                "SG303",
                "Bangalore",
                "Kolkata",
                now.plusDays(3).withHour(10).withMinute(30),
                now.plusDays(3).withHour(13).withMinute(0),
                new BigDecimal("2799.99"),
                120
        );
    
        // Flight 4: Kolkata to Hyderabad
        Flight flight4 = new Flight(
                "UK404",
                "Kolkata",
                "Hyderabad",
                now.plusDays(4).withHour(16).withMinute(0),
                now.plusDays(4).withHour(18).withMinute(30),
                new BigDecimal("2499.99"),
                140
        );
    
        // Flight 5: Hyderabad to Chennai
        Flight flight5 = new Flight(
                "G8505",
                "Hyderabad",
                "Chennai",
                now.plusDays(5).withHour(20).withMinute(0),
                now.plusDays(5).withHour(21).withMinute(15),
                new BigDecimal("1999.99"),
                160
        );
    
        // Flight 6: Chennai to Kochi
        Flight flight6 = new Flight(
                "AI606",
                "Chennai",
                "Kochi",
                now.plusDays(1).withHour(6).withMinute(30),
                now.plusDays(1).withHour(7).withMinute(45),
                new BigDecimal("2199.99"),
                130
        );
    
        // Flight 7: Pune to Delhi
        Flight flight7 = new Flight(
                "6E707",
                "Pune",
                "Delhi",
                now.plusDays(2).withHour(9).withMinute(15),
                now.plusDays(2).withHour(11).withMinute(30),
                new BigDecimal("4599.99"),
                170
        );
    
        // Flight 8: Ahmedabad to Jaipur
        Flight flight8 = new Flight(
                "SG808",
                "Ahmedabad",
                "Jaipur",
                now.plusDays(3).withHour(12).withMinute(0),
                now.plusDays(3).withHour(13).withMinute(20),
                new BigDecimal("1899.99"),
                110
        );
    
        // Flight 9: Goa to Mumbai
        Flight flight9 = new Flight(
                "UK909",
                "Goa",
                "Mumbai",
                now.plusDays(1).withHour(18).withMinute(45),
                now.plusDays(1).withHour(20).withMinute(0),
                new BigDecimal("3299.99"),
                145
        );
    
        // Flight 10: Lucknow to Bangalore
        Flight flight10 = new Flight(
                "AI110",
                "Lucknow",
                "Bangalore",
                now.plusDays(4).withHour(7).withMinute(0),
                now.plusDays(4).withHour(9).withMinute(40),
                new BigDecimal("4899.99"),
                155
        );
    
        // Flight 11: Chandigarh to Delhi
        Flight flight11 = new Flight(
                "6E111",
                "Chandigarh",
                "Delhi",
                now.plusDays(2).withHour(13).withMinute(10),
                now.plusDays(2).withHour(14).withMinute(5),
                new BigDecimal("1599.99"),
                100
        );
    
        // Flight 12: Bhubaneswar to Kolkata
        Flight flight12 = new Flight(
                "SG212",
                "Bhubaneswar",
                "Kolkata",
                now.plusDays(5).withHour(11).withMinute(25),
                now.plusDays(5).withHour(12).withMinute(35),
                new BigDecimal("1799.99"),
                125
        );
    
        // Flight 13: Nagpur to Hyderabad
        Flight flight13 = new Flight(
                "AI313",
                "Nagpur",
                "Hyderabad",
                now.plusDays(3).withHour(15).withMinute(40),
                now.plusDays(3).withHour(17).withMinute(0),
                new BigDecimal("2099.99"),
                135
        );
    
        // Flight 14: Srinagar to Delhi
        Flight flight14 = new Flight(
                "UK414",
                "Srinagar",
                "Delhi",
                now.plusDays(6).withHour(8).withMinute(20),
                now.plusDays(6).withHour(10).withMinute(0),
                new BigDecimal("5499.99"),
                140
        );
    
        // Flight 15: Visakhapatnam to Chennai
        Flight flight15 = new Flight(
                "G815",
                "Visakhapatnam",
                "Chennai",
                now.plusDays(2).withHour(17).withMinute(15),
                now.plusDays(2).withHour(18).withMinute(45),
                new BigDecimal("2599.99"),
                115
        );
    
        // save them to the DB
        flightService.saveFlight(flight1);
        flightService.saveFlight(flight2);
        flightService.saveFlight(flight3);
        flightService.saveFlight(flight4);
        flightService.saveFlight(flight5);
        flightService.saveFlight(flight6);
        flightService.saveFlight(flight7);
        flightService.saveFlight(flight8);
        flightService.saveFlight(flight9);
        flightService.saveFlight(flight10);
        flightService.saveFlight(flight11);
        flightService.saveFlight(flight12);
        flightService.saveFlight(flight13);
        flightService.saveFlight(flight14);
        flightService.saveFlight(flight15);
    }
}
