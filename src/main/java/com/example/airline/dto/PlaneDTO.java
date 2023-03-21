package com.example.airline.dto;

import com.example.airline.model.enumerations.PlaneStatusType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PlaneDTO {
    Long id;
    String registrationNumber;
    PlaneModelDTO planeModelDTO;
    PlaneStatusType status;
    AirportDTO airportDTO;
    GroundServiceDTO groundServiceDTO;
}
