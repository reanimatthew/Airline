package com.example.airline.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class FlightDTO {

    @Positive
    Long id;

    @NotBlank
    String flightNumber;

    @Pattern(regexp = "[A-Z]{3}")
    AirportDTO departureAirportDTO;

    @Pattern(regexp = "[A-Z]{3}")
    AirportDTO arrivalAirportDTO;

    @NotBlank
    ZonedDateTime departureDate;

    @NotBlank
    ZonedDateTime arrivalDate;

    @NotBlank
    PlaneDTO planeDTO;

    @Positive
    BigDecimal basePrice;
}
