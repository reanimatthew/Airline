//package com.example.airline;
//
//import com.example.airline.dto.AirportDTO;
//import com.example.airline.model.Airport;
//import com.example.airline.repositories.AirportRepository;
//import com.example.airline.service.AirportService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//public class AirportServiceTests {
//    @Autowired
//    AirportService airportService;
//
//    @Autowired
//    AirportRepository airportRepository;
//
//    @Test
//    void getAllTest() {
//        int airportsCount = 5;
//        for (int i = 0; i < 5; i++) {
//            airportRepository.save(Airport.builder().build());
//        }
//
//        List<AirportDTO> airportServiceAll = airportService.getAll();
//        Assertions.assertEquals(airportsCount, airportServiceAll.size());
//    }
//
//
//}
