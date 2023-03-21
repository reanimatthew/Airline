package com.example.airline.repositories;

import com.example.airline.model.GroundService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroundServiceRepository extends JpaRepository<GroundService, Long> {
    Optional<GroundService> findGroundServiceByName(String groundServiceName);
}
