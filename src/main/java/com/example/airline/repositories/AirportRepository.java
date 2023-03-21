package com.example.airline.repositories;

import com.example.airline.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findAirportByIata(String iata);
    boolean existsById(Long id);

}
