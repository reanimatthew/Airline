package com.example.airline.service;

import com.example.airline.exceptions.EntityNotFoundByIdException;
import com.example.airline.model.Flight;
import com.example.airline.repositories.FlightRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightService {
    FlightRepository flightRepository;

    public Page<Flight> getAll(int offset,
                               int limit,
                               String cityDepart,
                               String cityArrive,
                               ZonedDateTime afterDepartDate,
                               ZonedDateTime beforeDepartDate,
                               LocalDate minDate,
                               LocalDate maxDate,
                               BigDecimal price,
                               String planeILike
    ) {
        List<Specification<Flight>> filters = new ArrayList<>();
//        if (cityDepart != null) {
//            Specification<Flight> cityDepartEquals = FlightRepository.cityDepartEqual(cityDepart);
//            filters.add(cityDepartEquals);
//        }
//
//        if (cityArrive != null) {
//            Specification<Flight> cityArriveEquals = FlightRepository.cityArriveEqual(cityArrive);
//            filters.add(cityArriveEquals);
//        }
        Optional.ofNullable(cityDepart).ifPresent(cd -> filters.add(FlightRepository.cityDepartEqual(cd)));
        Optional.ofNullable(cityArrive).ifPresent(ca -> filters.add(FlightRepository.cityArriveEqual(ca)));
        Optional.ofNullable(afterDepartDate).ifPresent(ad -> filters.add(FlightRepository.afterDepartureDate(ad)));
        Optional.ofNullable(beforeDepartDate).ifPresent(bd -> filters.add(FlightRepository.beforeDepartureDate(bd)));

        //ToDo пусть можно будет minDate / maxDate - null
        if(minDate != null && maxDate != null) {
            Specification<Flight> minMax = FlightRepository.departureDateOnlyBetween(minDate, maxDate);
            filters.add(minMax);
        }

        Optional.ofNullable(price).ifPresent(p -> filters.add(FlightRepository.basePriceLess(p)));
        Optional.ofNullable(planeILike).ifPresent(pl -> filters.add(FlightRepository.cityArriveEqual(pl)));

        Specification<Flight> result = filters.stream().reduce(Specification::and).orElse(null);

        Pageable pageable = PageRequest.of(offset, limit);
        return flightRepository.findAll(result, pageable);
    }

    public Flight getByNumber(String flightNumber) {
        return flightRepository.findFlightByFlightNumber(flightNumber).orElseThrow();
    }

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getByDepartureAirport_City(String city, int pageNumber, int elementsOnPage, String sort) {
        Pageable pageable = PageRequest.of(pageNumber, elementsOnPage, Sort.by(sort));
        return flightRepository.findByDepartureAirport_City(city, pageable);
    }

    public List<Flight> getByArrivalAirport_City(String city, int pageNumber, int elementsOnPage, String sort) {
        Pageable pageable = PageRequest.of(pageNumber, elementsOnPage, Sort.by(sort));
        return flightRepository.findByArrivalAirport_City(city, pageable);
    }

    public List<Flight> getByDepartureDateOnly(int year, int month, int day, int pageNumber, int elementsOnPage, String sort) {
        LocalDate date = LocalDate.of(year, month, day);
        Pageable pageable = PageRequest.of(pageNumber, elementsOnPage, Sort.by(sort));
        return flightRepository.findByDepartureDateOnly(date, pageable);
    }

    public List<Flight> getByArrivalDate(int year, int month, int day, String zoneId, int pageNumber, int elementsOnPage, String sort) {
        ZonedDateTime startDate = ZonedDateTime.of(LocalDateTime.of(year, month, day, 0, 0, 0), ZoneId.of(zoneId));
        ZonedDateTime finishDate = ZonedDateTime.of(LocalDateTime.of(year, month, day, 23, 59, 59), ZoneId.of(zoneId));
        Pageable pageable = PageRequest.of(pageNumber, elementsOnPage, Sort.by(sort));
        return flightRepository.findByArrivalDateBetween(startDate, finishDate, pageable);
    }

    public Flight getById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new EntityNotFoundByIdException(id, "flight"));
    }

}
