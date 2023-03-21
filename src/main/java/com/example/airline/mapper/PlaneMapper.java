package com.example.airline.mapper;

import com.example.airline.dto.AirportDTO;
import com.example.airline.dto.GroundServiceDTO;
import com.example.airline.dto.PlaneDTO;
import com.example.airline.dto.PlaneModelDTO;
import com.example.airline.model.Airport;
import com.example.airline.model.GroundService;
import com.example.airline.model.Plane;
import com.example.airline.model.PlaneModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaneMapper {
    PlaneModelMapper planeModelMapper;
    AirportMapper airportMapper;
    GroundServiceMapper groundServiceMapper;

    public PlaneDTO mapToDTO(Plane plane) {
        PlaneModelDTO planeModelDTO = planeModelMapper.mapToDTO(plane.getPlaneModel());
        AirportDTO airportDTO = airportMapper.mapToDTO(plane.getAirport());
        GroundServiceDTO groundServiceDTO = groundServiceMapper.mapToDTO(plane.getGroundService());
        return PlaneDTO
                .builder()
                .id(plane.getId())
                .registrationNumber(plane.getRegistrationNumber())
                .planeModelDTO(planeModelDTO)
                .status(plane.getStatus())
                .airportDTO(airportDTO)
                .groundServiceDTO(groundServiceDTO)
                .build();
    }

    public Plane mapToEntity(PlaneDTO planeDTO) {
        PlaneModel planeModel = planeModelMapper.mapToEntity(planeDTO.getPlaneModelDTO());
        Airport airport = airportMapper.mapToEntity(planeDTO.getAirportDTO());
        GroundService groundService = groundServiceMapper.mapToEntity(planeDTO.getGroundServiceDTO());
        return Plane
                .builder()
                .id(planeDTO.getId())
                .registrationNumber(planeDTO.getRegistrationNumber())
                .planeModel(planeModel)
                .status(planeDTO.getStatus())
                .airport(airport)
                .groundService(groundService)
                .build();
    }

    public List<PlaneDTO> mapToListDTO(List<Plane> planes) {
        List<PlaneDTO> planeDTOs = new ArrayList<>();
        for (Plane entity : planes
        ) {
            planeDTOs.add(mapToDTO(entity));
        }
        return planeDTOs;
    }

    public List<Plane> mapToListEntity(List<PlaneDTO> planeDTOs) {
        List<Plane> planes = new ArrayList<>();
        for (PlaneDTO dto : planeDTOs
        ) {
            planes.add(mapToEntity(dto));
        }
        return planes;
    }
}
