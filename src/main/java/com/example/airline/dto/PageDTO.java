package com.example.airline.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PageDTO<T> {

    List<T> content;

    @PositiveOrZero
    int offset;

    @PositiveOrZero
    int limit;

    @PositiveOrZero
    int totalPages;
}
