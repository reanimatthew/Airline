package com.example.airline.dto;

import com.example.airline.model.enumerations.TicketType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TicketToSaveDTO {

//    @NotNull
//    @Positive(message = "passengerID must be positive digit")
//    Long passengerID;

    @NotNull
    @Positive
    Long flightID;

    @NotNull
    TicketType ticketType;
}
