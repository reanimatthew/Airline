package com.example.airline.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.time.ZoneId;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AirportDTO {
    Long id;

    @Pattern(regexp = "[A-Z]{3}")
    String iata;

    @NotBlank
    String city;

    @Positive
    BigInteger capacity;

    @NotBlank
    ZoneId zoneId;
}
