package com.example.airline.api;

import com.example.airline.dto.PassengerDTO;
import com.example.airline.dto.PassengerToSaveDTO;
import com.example.airline.mapper.PassengerMapper;
import com.example.airline.model.Passenger;
import com.example.airline.service.PassengerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/passengers")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PassengerAPI {
    PassengerService passengerService;
    PassengerMapper passengerMapper;

    @GetMapping
    List<PassengerDTO> getAll() {
        return passengerMapper.mapToListDTO(passengerService.getAll());
    }

    @GetMapping("passport")
    PassengerDTO getByPassport(String passport) {
        return passengerMapper.mapToDTO(passengerService.getByPassport(passport));
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    PassengerDTO getById(@PathVariable Long id) {
        return passengerMapper.mapToDTO(passengerService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PassengerDTO save(@RequestBody PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.mapToEntity(passengerDTO);
        return passengerMapper.mapToDTO(passengerService.save(passenger));
    }

    @PostMapping("registration")
    @ResponseStatus(HttpStatus.CREATED)
    PassengerDTO registration(@RequestBody @Valid PassengerToSaveDTO passengerToSaveDTO) {
        String passport = passengerToSaveDTO.getPassport();
        String surname = passengerToSaveDTO.getSurname();
        String name = passengerToSaveDTO.getName();
        String patronymic = passengerToSaveDTO.getPatronymic();
        BigDecimal age = passengerToSaveDTO.getAge();
        return passengerMapper.mapToDTO(passengerService.registration(passport, surname, name, patronymic, age));
    }

}
