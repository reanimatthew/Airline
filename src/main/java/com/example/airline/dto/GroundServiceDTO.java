package com.example.airline.dto;

import com.example.airline.model.enumerations.GroundServiceType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GroundServiceDTO {
    Long id;
    String name;
    AirportDTO airportDTO;
    GroundServiceType groundServiceType;
}
