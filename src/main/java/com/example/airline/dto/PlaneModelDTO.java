package com.example.airline.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PlaneModelDTO {
    Long id;
    String manufacturer;
    String model;
    BigInteger numberOfSeat;
    BigInteger numberOfCrewMembers;
}
