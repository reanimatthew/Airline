package com.example.airline.dto;

import com.example.airline.model.enumerations.PositionType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeDTO {
    Long id;
    String passport;
    String surname;
    String name;
    String patronymic;
    BigDecimal age;
    PositionType positionType;
    BigDecimal salary;
    BigDecimal experience;
    FlightDTO flightDTO;
}
