package com.example.airline.repositories;

import com.example.airline.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    Optional<Plane> findPlaneByRegistrationNumber(String registrationNumber);
}
