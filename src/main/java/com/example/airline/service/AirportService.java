package com.example.airline.service;

import com.example.airline.model.Airport;
import com.example.airline.repositories.AirportRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AirportService {
    AirportRepository airportRepository;

    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    public Airport getByIATA(String iata) {
        return airportRepository.findAirportByIata(iata).orElseThrow();
    }

    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }
}
