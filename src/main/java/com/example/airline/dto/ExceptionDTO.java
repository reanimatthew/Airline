package com.example.airline.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ExceptionDTO {
    @NotNull
    String message;

    @NotBlank
    ZonedDateTime dateTime;

    @NotBlank
    String type;

    @NotBlank
    String path;
}
