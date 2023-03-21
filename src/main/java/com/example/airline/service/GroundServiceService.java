package com.example.airline.service;

import com.example.airline.dto.GroundServiceDTO;
import com.example.airline.mapper.GroundServiceMapper;
import com.example.airline.model.GroundService;
import com.example.airline.repositories.GroundServiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GroundServiceService {
    GroundServiceRepository groundServiceRepository;
    GroundServiceMapper groundServiceMapper;

    public List<GroundService> getAll() {
        return groundServiceRepository.findAll();
    }

    public GroundService getByName(String name) {
        return groundServiceRepository.findGroundServiceByName(name).orElseThrow();
    }

    public GroundService save(GroundService groundService) {
        return groundServiceRepository.save(groundService);
    }
}
