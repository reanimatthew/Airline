package com.example.airline.api;

import com.example.airline.dto.*;
import com.example.airline.mapper.TicketMapper;
import com.example.airline.model.Passenger;
import com.example.airline.model.Ticket;
import com.example.airline.model.enumerations.TicketType;
import com.example.airline.service.PassengerService;
import com.example.airline.service.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketAPI {
    TicketService ticketService;
    TicketMapper ticketMapper;
    PassengerService passengerService;

    @GetMapping
    List<TicketDTO> getAll() {
        return ticketMapper.mapToListDTO(ticketService.getAll());
    }

    @GetMapping("ticketnumber")
    TicketDTO getByTicketNumber(String ticketNumber) {
        return ticketMapper.mapToDTO(ticketService.getByTicketNumber(ticketNumber));
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    TicketDTO save(@RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.mapToEntity(ticketDTO);
        return ticketMapper.mapToDTO(ticketService.save(ticket));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TicketDTO ordering(@RequestBody @Valid TicketToSaveDTO ticketToSaveDTO) {
        Long passengerID = passengerService.getCurrentAuthenticatedPassenger().getId();
        Long flightID = ticketToSaveDTO.getFlightID();
        TicketType ticketType = ticketToSaveDTO.getTicketType();
        return ticketMapper.mapToDTO(ticketService.ticketOrder(passengerID, flightID, ticketType));
    }

    @PostMapping("registrationAndOrdering")
    @ResponseStatus(HttpStatus.CREATED)
    TicketDTO registrationAndOrdering(@RequestBody @Valid PassAndTickRegAndOrderDTO passAndTickRegAndOrderDTO) {
        Passenger passenger = passengerService.registration(
                passAndTickRegAndOrderDTO.getPassport(),
                passAndTickRegAndOrderDTO.getSurname(),
                passAndTickRegAndOrderDTO.getName(),
                passAndTickRegAndOrderDTO.getPatronymic(),
                passAndTickRegAndOrderDTO.getAge()
        );

        Ticket ticket = ticketService.ticketOrder(
                passenger.getId(),
                passAndTickRegAndOrderDTO.getFlightID(),
                passAndTickRegAndOrderDTO.getTicketType()
        );

        return ticketMapper.mapToDTO(ticket);
    }
}
