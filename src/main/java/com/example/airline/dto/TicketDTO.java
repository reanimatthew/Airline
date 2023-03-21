package com.example.airline.dto;

import com.example.airline.model.Flight;
import com.example.airline.model.Passenger;
import com.example.airline.model.enumerations.TicketType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class TicketDTO {
    Long id;

    @NotBlank
    String ticketNumber;

    @NotNull
    @Positive
    BigDecimal price;

    @NotNull
    PassengerDTO passengerDTO;

    @NotNull
    TicketType ticketType;

    @NotNull
    FlightDTO flightDTO;
}
