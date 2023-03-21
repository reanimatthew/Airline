package com.example.airline.mapper;

import com.example.airline.dto.AirportDTO;
import com.example.airline.dto.GroundServiceDTO;
import com.example.airline.model.Airport;
import com.example.airline.model.GroundService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroundServiceMapper {
    AirportMapper airportMapper;

    public GroundServiceDTO mapToDTO(GroundService groundService) {
        AirportDTO airportDTO = airportMapper.mapToDTO(groundService.getAirport());
        return GroundServiceDTO
                .builder()
                .id(groundService.getId())
                .name(groundService.getName())
                .airportDTO(airportDTO)
                .groundServiceType(groundService.getGroundServiceType())
                .build();
    }

    public GroundService mapToEntity(GroundServiceDTO groundServiceDTO) {
        Airport airport = airportMapper.mapToEntity(groundServiceDTO.getAirportDTO());
        return GroundService
                .builder()
                .id(groundServiceDTO.getId())
                .name(groundServiceDTO.getName())
                .airport(airport)
                .groundServiceType(groundServiceDTO.getGroundServiceType())
                .build();
    }

    public List<GroundServiceDTO> mapToListDTO(List<GroundService> groundServices) {
        List<GroundServiceDTO> groundServiceDTOs = new ArrayList<>();
        for (GroundService entity : groundServices
        ) {
            groundServiceDTOs.add(mapToDTO(entity));
        }
        return groundServiceDTOs;
    }

    public List<GroundService> mapToListEntity(List<GroundServiceDTO> groundServiceDTOs) {
        List<GroundService> groundServices = new ArrayList<>();
        for (GroundServiceDTO dto : groundServiceDTOs
        ) {
            groundServices.add(mapToEntity(dto));
        }
        return groundServices;
    }
}
