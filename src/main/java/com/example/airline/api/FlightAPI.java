package com.example.airline.api;

import com.example.airline.dto.FlightDTO;
import com.example.airline.dto.PageDTO;
import com.example.airline.mapper.FlightMapper;
import com.example.airline.mapper.PageMapper;
import com.example.airline.model.Flight;
import com.example.airline.service.FlightService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/flights")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightAPI {
    FlightService flightService;
    FlightMapper flightMapper;
    PageMapper pageMapper;


//    @GetMapping
//    Page<Flight> get(@RequestParam(required = false, defaultValue = "0") int offset,
//                        @RequestParam(required = false, defaultValue = "50") int limit,
//                        @RequestParam(required = false) String cityDepart,
//                        @RequestParam(required = false) String cityArrive) {
////        List<Flight> flights = flightService.getAll(offset, limit, cityDepart, cityArrive).getContent();
////        return flightMapper.mapToListDTO(flights);
//        return flightService.getAll(offset, limit, cityDepart, cityArrive);
//    }

    @GetMapping
    PageDTO<Flight> get(@RequestParam(required = false, defaultValue = "0") int offset,
                        @RequestParam(required = false, defaultValue = "50") int limit,
                        @RequestParam(required = false) String cityDepart,
                        @RequestParam(required = false) String cityArrive,
                        @RequestParam(required = false) ZonedDateTime afterDepartDate,
                        @RequestParam(required = false) ZonedDateTime beforeDepartDate,
                        @RequestParam(required = false) LocalDate minDate,
                        @RequestParam(required = false) LocalDate maxDate,
                        @RequestParam(required = false) BigDecimal price,
                        @RequestParam(required = false) String planeILike
    ) {
        Page<Flight> flightPage = flightService.getAll(
                offset,
                limit,
                cityDepart,
                cityArrive,
                afterDepartDate,
                beforeDepartDate,
                minDate,
                maxDate,
                price,
                planeILike
        );
        return pageMapper.mapToDTO(flightPage);
    }

    @GetMapping("{id}")
    FlightDTO getById(@PathVariable Long id) {
        return flightMapper.mapToDTO(flightService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    FlightDTO save(@RequestBody FlightDTO flightDTO) {
        Flight flight = flightMapper.mapToEntity(flightDTO);
        return flightMapper.mapToDTO(flightService.save(flight));
    }

}
