package com.example.airline.service;

import com.example.airline.mapper.PlaneModelMapper;
import com.example.airline.model.PlaneModel;
import com.example.airline.repositories.PlaneModelRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaneModelService {
    PlaneModelRepository planeModelRepository;

    public List<PlaneModel> getAll() {
        return planeModelRepository.findAll();
    }

    public PlaneModel getByModel(String model) {
        return planeModelRepository.findPlaneModelByModel(model).orElseThrow();
    }

    public PlaneModel save(PlaneModel planeModel) {
        return planeModelRepository.save(planeModel);
    }
}
