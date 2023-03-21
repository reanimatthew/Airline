package com.example.airline.service;

import com.example.airline.model.Flight;
import com.example.airline.model.Passenger;
import com.example.airline.model.Ticket;
import com.example.airline.model.enumerations.TicketType;
import com.example.airline.repositories.TicketRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketService {
    TicketRepository ticketRepository;
    PassengerService passengerService;
    FlightService flightService;

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Ticket getByTicketNumber(String ticketNumber) {
        return ticketRepository.findByTicketNumber(ticketNumber).orElseThrow();
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

//    public String getNewTicketDTO(String passport, Long id, String ticketType) {
//        PassengerDTO passengerDTO = passengerService.getByPassport(passport);
//        FlightDTO flightDTO = flightService.getById(id);
//        Flight flight = flightMapper.mapToEntity(flightDTO);
//        String ticketNumber = flight.getFlightNumber() + ":" + passport.hashCode();
//        BigDecimal basePrice = flightDTO.getBasePrice();
//        BigDecimal price = basePrice.multiply(TicketType.valueOf(ticketType).getExtraСharge()).multiply(passengerDTO.getLevel().getDiscount());
//        Ticket ticket = Ticket
//                .builder()
//                .flight(flightMapper.mapToEntity(flightDTO))
//                .ticketNumber(ticketNumber)
//                .ticketType(TicketType.valueOf(ticketType))
//                .passenger(passengerMapper.mapToEntity(passengerDTO))
//                .price(price)
//                .build();
//        ticketRepository.save(ticket);
//        return ticket.getTicketNumber();
//    }

    public Ticket ticketOrder(Long passengerId, Long flightId, TicketType ticketType) {
//        UUID uuid = UUID.randomUUID();
        Passenger passenger = passengerService.getById(passengerId);
        Flight flight = flightService.getById(flightId);
        String ticketNumber = flight.getFlightNumber() + ":" + passenger.getPassport().hashCode();
        BigDecimal basePrice = flight.getBasePrice();
        BigDecimal price = basePrice.multiply(ticketType.getExtraCharge()).multiply(passenger.getLevel().getDiscount());
        Ticket ticket = Ticket
                .builder()
                .flight(flight)
                .ticketNumber(ticketNumber)
                .ticketType(ticketType)
                .passenger(passenger)
                .price(price)
                .build();
        return ticketRepository.save(ticket);
    }
}
