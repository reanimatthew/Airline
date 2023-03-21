package com.example.airline.security;

import com.example.airline.model.Passenger;
import com.example.airline.repositories.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

// здесь мы получаем по имени пользователя (логину) пассажира, обернутого в UserDetailsImpl
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PassengerRepository passengerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Passenger passenger = passengerRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(""));
        return new UserDetailsImpl(passenger);
    }
}
