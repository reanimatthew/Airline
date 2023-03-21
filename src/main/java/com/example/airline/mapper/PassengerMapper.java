package com.example.airline.mapper;

import com.example.airline.dto.GroundServiceDTO;
import com.example.airline.dto.PassengerDTO;
import com.example.airline.dto.TicketDTO;
import com.example.airline.model.GroundService;
import com.example.airline.model.Passenger;
import com.example.airline.model.Ticket;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PassengerMapper {
    //ToDo и вот тут еще цикл TicketMapper <-> PassengerMapper, Spring ругается
//    TicketMapper ticketMapper;

    public PassengerDTO mapToDTO(Passenger passenger) {
//        List<TicketDTO> ticketDTOs = ticketMapper.mapToListDTO(passenger.getTickets());
        return PassengerDTO
                .builder()
                .id(passenger.getId())
                .passport(passenger.getPassport())
                .surname(passenger.getSurname())
                .name(passenger.getName())
                .patronymic(passenger.getPatronymic())
                .age(passenger.getAge())
                .level(passenger.getLevel())
//                .ticketDTOs(ticketDTOs)
                .build();
    }

    public Passenger mapToEntity(PassengerDTO passengerDTO) {
//        List<Ticket> tickets = ticketMapper.mapToListEntity(passengerDTO.getTicketDTOs());
        return Passenger
                .builder()
                .id(passengerDTO.getId())
                .passport(passengerDTO.getPassport())
                .surname(passengerDTO.getSurname())
                .name(passengerDTO.getName())
                .patronymic(passengerDTO.getPatronymic())
                .age(passengerDTO.getAge())
                .level(passengerDTO.getLevel())
//                .tickets(tickets)
                .build();
    }

    public List<PassengerDTO> mapToListDTO(List<Passenger> passengers) {
        List<PassengerDTO> passengerDTOs = new ArrayList<>();
        for (Passenger entity : passengers
        ) {
            passengerDTOs.add(mapToDTO(entity));
        }
        return passengerDTOs;
    }

    public List<Passenger> mapToListEntity(List<PassengerDTO> passengerDTOs) {
        List<Passenger> passengers = new ArrayList<>();
        for (PassengerDTO dto : passengerDTOs
        ) {
            passengers.add(mapToEntity(dto));
        }
        return passengers;
    }
}
