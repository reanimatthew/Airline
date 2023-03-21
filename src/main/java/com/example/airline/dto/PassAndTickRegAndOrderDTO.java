package com.example.airline.dto;

import com.example.airline.model.enumerations.TicketType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PassAndTickRegAndOrderDTO {

    @NotBlank
    String passport;

    @NotBlank
    String surname;

    @NotBlank
    String name;

    @NotBlank
    String patronymic;

    @PositiveOrZero
    BigDecimal age;

    @Positive
    Long flightID;

    TicketType ticketType;
}
