package com.example.airline.api;

import com.example.airline.dto.AirportDTO;
import com.example.airline.mapper.AirportMapper;
import com.example.airline.model.Airport;
import com.example.airline.service.AirportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/airports")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class AirportAPI {
    AirportService airportService;
    AirportMapper airportMapper;

    @GetMapping
    List<AirportDTO> getAll() {
        log.info("Get all airports");
        return airportMapper.mapToListDTO(airportService.getAll());
    }

    @GetMapping("name")
    AirportDTO getByIATA(String iata) {
        log.info("Get by IATA");
        return airportMapper.mapToDTO(airportService.getByIATA(iata));
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    AirportDTO save(@RequestBody AirportDTO airportDTO) {
        log.info("Save new airport");
        Airport airport = airportMapper.mapToEntity(airportDTO);
        return airportMapper.mapToDTO(airportService.save(airport));
    }
}
