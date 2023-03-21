package com.example.airline.repositories;

import com.example.airline.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findPassengerByPassport(String passport);
    Optional<Passenger> findByLogin(String login);
}
