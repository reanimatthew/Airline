package com.example.airline.repositories;

import com.example.airline.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long>, QueryByExampleExecutor<Flight>, JpaSpecificationExecutor<Flight> {
    Optional<Flight> findFlightByFlightNumber(String flightNumber);

    List<Flight> findByDepartureAirport_City(String city, Pageable pageable);
    List<Flight> findByArrivalAirport_City(String city, Pageable pageable);
    List<Flight> findByDepartureDateOnly(LocalDate dateOnly, Pageable pageable);
    List<Flight> findByArrivalDateBetween(ZonedDateTime startDate, ZonedDateTime finishDate, Pageable pageable);

    @Query("SELECT f FROM Flight f WHERE f.departureAirport.city=:cityDepart AND f.arrivalAirport.city=:cityArrive")
    Page<Flight> findByFilters(@Param("cityDepart") String cityDepart, @Param("cityArrive") String cityArrive, Pageable pageable);

    static Specification<Flight> cityDepartEqual(String cityDepart) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join(Flight_.departureAirport).get(Airport_.city), cityDepart);
    }

    static Specification<Flight> cityArriveEqual(String cityArrive) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join(Flight_.arrivalAirport).get(Airport_.city), cityArrive);
    }

    static Specification<Flight> afterDepartureDate(ZonedDateTime dateTime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(Flight_.departureDate), dateTime);
    }

    static Specification<Flight> beforeDepartureDate(ZonedDateTime dateTime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(Flight_.departureDate), dateTime);
    }

    static Specification<Flight> departureDateOnlyBetween(LocalDate firstDate, LocalDate lastDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Flight_.departureDateOnly), firstDate, lastDate);
    }

    static Specification<Flight> basePriceLess(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(Flight_.basePrice), price);
    }

    static Specification<Flight> planeEquals(String planeILike) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join(Flight_.plane).join(Plane_.planeModel).get(PlaneModel_.model), planeILike);
    }

}
