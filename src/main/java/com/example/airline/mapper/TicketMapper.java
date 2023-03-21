package com.example.airline.mapper;

import com.example.airline.dto.FlightDTO;
import com.example.airline.dto.PassengerDTO;
import com.example.airline.dto.PlaneModelDTO;
import com.example.airline.dto.TicketDTO;
import com.example.airline.model.Flight;
import com.example.airline.model.Passenger;
import com.example.airline.model.PlaneModel;
import com.example.airline.model.Ticket;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketMapper {
    PassengerMapper passengerMapper;
    FlightMapper flightMapper;

    public TicketDTO mapToDTO(Ticket ticket) {
        PassengerDTO passengerDTO = passengerMapper.mapToDTO(ticket.getPassenger());
        FlightDTO flightDTO = flightMapper.mapToDTO(ticket.getFlight());
        return TicketDTO
                .builder()
                .id(ticket.getId())
                .ticketNumber(ticket.getTicketNumber())
                .price(ticket.getPrice())
                .passengerDTO(passengerDTO)
                .ticketType(ticket.getTicketType())
                .flightDTO(flightDTO)
                .build();
    }

    public Ticket mapToEntity(TicketDTO ticketDTO) {
        Passenger passenger = passengerMapper.mapToEntity(ticketDTO.getPassengerDTO());
        Flight flight = flightMapper.mapToEntity(ticketDTO.getFlightDTO());
        return Ticket
                .builder()
                .id(ticketDTO.getId())
                .ticketNumber(ticketDTO.getTicketNumber())
                .price(ticketDTO.getPrice())
                .passenger(passenger)
                .ticketType(ticketDTO.getTicketType())
                .flight(flight)
                .build();
    }

    public List<TicketDTO> mapToListDTO(List<Ticket> tickets) {
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket entity : tickets
        ) {
            ticketDTOs.add(mapToDTO(entity));
        }
        return ticketDTOs;
    }

    public List<Ticket> mapToListEntity(List<TicketDTO> ticketDTOs) {
        List<Ticket> tickets = new ArrayList<>();
        for (TicketDTO dto : ticketDTOs
        ) {
            tickets.add(mapToEntity(dto));
        }
        return tickets;
    }
}
