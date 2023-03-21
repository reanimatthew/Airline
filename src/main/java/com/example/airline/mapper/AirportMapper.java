package com.example.airline.mapper;

import com.example.airline.dto.AirportDTO;
import com.example.airline.model.Airport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirportMapper {

    public AirportDTO mapToDTO(Airport airport) {
        return AirportDTO
                .builder()
                .id(airport.getId())
                .iata(airport.getIata())
                .city(airport.getCity())
                .capacity(airport.getCapacity())
                .zoneId(airport.getZoneId())
                .build();
    }

    public Airport mapToEntity(AirportDTO airportDTO) {
        return Airport
                .builder()
                .id(airportDTO.getId())
                .iata(airportDTO.getIata())
                .city(airportDTO.getCity())
                .capacity(airportDTO.getCapacity())
                .zoneId(airportDTO.getZoneId())
                .build();
    }

    public List<AirportDTO> mapToListDTO(List<Airport> airports) {
        return airports.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<Airport> mapToListEntity(List<AirportDTO> airportDTOs) {
        List<Airport> airports = new ArrayList<>();
        for (AirportDTO dto : airportDTOs
             ) {
            airports.add(mapToEntity(dto));
        }
        return airports;
    }
}
