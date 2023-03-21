package com.example.airline.api;

import com.example.airline.dto.FlightDTO;
import com.example.airline.mapper.FlightMapper;
import com.example.airline.service.AirportService;
import com.example.airline.service.FlightService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/search")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchAPI {
    FlightService flightService;
    FlightMapper flightMapper;

    @GetMapping("from")
    List<FlightDTO> findByDepartureCity(String city, int pageNumber, int elementsOnPage, String sort) {
        return flightMapper.mapToListDTO(flightService.getByDepartureAirport_City(city, pageNumber, elementsOnPage, sort));
    }

    @GetMapping("where")
    List<FlightDTO> findByArrivalCity(String city, int pageNumber, int elementsOnPage, String sort) {
        return flightMapper.mapToListDTO(flightService.getByArrivalAirport_City(city, pageNumber, elementsOnPage, sort));
    }

    @GetMapping("dateDeparture")
    List<FlightDTO> findByDataDeparture(int year, int month, int day, int pageNumber, int elementsOnPage, String sort) {
        return flightMapper.mapToListDTO(flightService.getByDepartureDateOnly(year, month, day, pageNumber, elementsOnPage, sort));
    }

    @GetMapping("dateArrive")
    List<FlightDTO> findByDataArrive(int year, int month, int day, String zoneId, int pageNumber, int elementsOnPage, String sort) {
        return flightMapper.mapToListDTO(flightService.getByArrivalDate(year, month, day, zoneId, pageNumber, elementsOnPage, sort));
    }

    @GetMapping("getFlight")
    FlightDTO findByNumber(String flightNumber) {
        return flightMapper.mapToDTO(flightService.getByNumber(flightNumber));
    }
}
