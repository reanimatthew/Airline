package com.example.airline;

import com.example.airline.model.Airport;
import com.example.airline.model.Airport_;
import com.example.airline.model.Flight;
import com.example.airline.model.Flight_;
import com.example.airline.repositories.AirportRepository;
import com.example.airline.repositories.FlightRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.swing.text.html.HTMLDocument;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FlightRepositoryTests {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportRepository airportRepository;

    @BeforeEach
    void init() {
        Airport airportDepart = Airport.builder()
                .city("Moscow")
                .build();

        Airport airportArrive = Airport.builder()
                .city("London")
                .build();

        airportRepository.save(airportDepart);
        airportRepository.save(airportArrive);

        Flight flight = Flight.builder()
                .departureAirport(airportDepart)
                .arrivalAirport(airportArrive)
                .flightNumber("10")
                .basePrice(BigDecimal.valueOf(10))
                .build();

        Flight flight2 = Flight.builder()
                .departureAirport(airportDepart)
                .arrivalAirport(airportArrive)
                .flightNumber("20")
                .basePrice(BigDecimal.valueOf(5))
                .build();

        Flight flight3 = Flight.builder()
                .departureAirport(airportDepart)
                .arrivalAirport(airportArrive)
                .flightNumber("101")
                .basePrice(BigDecimal.valueOf(15))
                .build();

        flightRepository.save(flight);
        flightRepository.save(flight2);
        flightRepository.save(flight3);
    }

    @Test
    void findByFilters_anyFilterIsNull() {
        Example<Flight> example = Example.of(
                Flight.builder()
                        .arrivalAirport(
                                Airport.builder()
                                        .city("London")
                                        .build()
                        )
                        .build()
        );
//        Optional<Flight> one = flightRepository.findOne(example);
        List<Flight> all = flightRepository.findAll(example);
//        System.out.println("one = " + one);
        all.forEach(System.out::println);
//        Page<Flight> flights = flightRepository.findByFilters(null, null, PageRequest.of(0, 100));
//        System.out.println("flights = " + flights);
    }

    @Test
    void findByFilters_anyFilterIsNull_withSpecifications() {
        BigDecimal maxPrice = BigDecimal.valueOf(20);
        Specification<Flight> priceLess = (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(Flight_.basePrice), maxPrice);
//        так тоже можно, но не надо:
//        Specification<Flight> priceLess1 = (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(Flight_.BASE_PRICE), maxPrice);
        List<Flight> flights = flightRepository.findAll(priceLess);
        // здесь импортирован метод!
        assertThat(flights).hasSize(3).doesNotHaveDuplicates();

        BigDecimal minPrice = BigDecimal.valueOf(7);
        Specification<Flight> priceGreater = (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(Flight_.basePrice), minPrice);
        Specification<Flight> resultSpec = priceLess.and(priceGreater);
        List<Flight> flights1 = flightRepository.findAll(resultSpec);
        assertThat(flights1).hasSize(2);

        String airportName = "Moscow";
        Specification<Flight> airportDepEquals = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join(Flight_.departureAirport).get(Airport_.city), airportName);
        resultSpec = resultSpec.and(airportDepEquals);
        List<Flight> flights2 = flightRepository.findAll(resultSpec);
        assertThat(flights2).hasSize(2);



    }

}
