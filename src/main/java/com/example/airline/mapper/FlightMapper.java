package com.example.airline.mapper;

import com.example.airline.dto.AirportDTO;
import com.example.airline.dto.FlightDTO;
import com.example.airline.dto.PlaneDTO;
import com.example.airline.model.Airport;
import com.example.airline.model.Flight;
import com.example.airline.model.Plane;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightMapper {
    AirportMapper airportMapper;
    PlaneMapper planeMapper;

    public FlightDTO mapToDTO(Flight flight) {
        AirportDTO departureAirportDTO = airportMapper.mapToDTO(flight.getDepartureAirport());
        AirportDTO arrivalAirportDTO = airportMapper.mapToDTO(flight.getArrivalAirport());
        PlaneDTO planeDTO = planeMapper.mapToDTO(flight.getPlane());
        return FlightDTO
                .builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .departureAirportDTO(departureAirportDTO)
                .arrivalAirportDTO(arrivalAirportDTO)
                .departureDate(flight.getDepartureDate())
                .arrivalDate(flight.getArrivalDate())
                .planeDTO(planeDTO)
                .basePrice(flight.getBasePrice())
                .build();
    }

    public Flight mapToEntity(FlightDTO flightDTO) {
        Airport departureAirport = airportMapper.mapToEntity(flightDTO.getDepartureAirportDTO());
        Airport arrivalAirport = airportMapper.mapToEntity(flightDTO.getArrivalAirportDTO());
        Plane plane = planeMapper.mapToEntity(flightDTO.getPlaneDTO());
        return Flight
                .builder()
                .id(flightDTO.getId())
                .flightNumber(flightDTO.getFlightNumber())
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDate(flightDTO.getDepartureDate())
                .arrivalDate(flightDTO.getArrivalDate())
                .plane(plane)
                .basePrice(flightDTO.getBasePrice())
                .build();
    }

    public List<FlightDTO> mapToListDTO(List<Flight> flights) {
        List<FlightDTO> flightDTOs = new ArrayList<>();
        for (Flight entity : flights
        ) {
            flightDTOs.add(mapToDTO(entity));
        }
        return flightDTOs;
    }

    public List<Flight> mapToListEntity(List<FlightDTO> flightDTOs) {
        List<Flight> flights = new ArrayList<>();
        for (FlightDTO dto : flightDTOs
        ) {
            flights.add(mapToEntity(dto));
        }
        return flights;
    }
}
