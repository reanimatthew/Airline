package com.example.airline.service;

import com.example.airline.dto.PlaneDTO;
import com.example.airline.mapper.PlaneMapper;
import com.example.airline.model.Plane;
import com.example.airline.repositories.PlaneRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaneService {
    PlaneRepository planeRepository;
    PlaneMapper planeMapper;

    public List<Plane> getAll() {
        return planeRepository.findAll();
    }

    public Plane getByRegistrationNumber(String registrationNumber) {
        return planeRepository.findPlaneByRegistrationNumber(registrationNumber).orElseThrow();
    }

    public Plane save(Plane plane) {
        return planeRepository.save(plane);
    }
}
