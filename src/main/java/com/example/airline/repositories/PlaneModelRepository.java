package com.example.airline.repositories;

import com.example.airline.model.PlaneModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneModelRepository extends JpaRepository<PlaneModel, Long> {
    Optional<PlaneModel> findPlaneModelByModel(String model);
}
