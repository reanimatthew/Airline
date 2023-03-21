package com.example.airline.service;

import com.example.airline.exceptions.EntityNotFoundByIdException;
import com.example.airline.model.Passenger;
import com.example.airline.model.enumerations.PassengerLevelType;
import com.example.airline.repositories.PassengerRepository;
import com.example.airline.security.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PassengerService {
    PassengerRepository passengerRepository;

    public List<Passenger> getAll() {
        return passengerRepository.findAll();
    }

    public Passenger getByPassport(String passport) {
        return passengerRepository.findPassengerByPassport(passport).orElseThrow();
    }

    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger getById(Long id) {
        return passengerRepository.findById(id).orElseThrow(() -> new EntityNotFoundByIdException(id, "passenger"));
    }

    public Passenger registration(String passport, String surname, String name, String patronymic, BigDecimal age) {
        Passenger passenger = Passenger
                .builder()
                .passport(passport)
                .surname(surname)
                .name(name)
                .patronymic(patronymic)
                .age(age)
                .level(PassengerLevelType.STANDARD)
                .build();
        return save(passenger);
    }

    public Passenger getCurrentAuthenticatedPassenger() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl)
            return ((UserDetailsImpl) principal).getPassenger();
        throw new RuntimeException();
    }
}
