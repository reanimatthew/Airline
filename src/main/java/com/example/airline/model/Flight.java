package com.example.airline.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String flightNumber;

    @ManyToOne
    Airport departureAirport;

    @ManyToOne
    Airport arrivalAirport;

    ZonedDateTime departureDate;
    ZonedDateTime arrivalDate;

    //ToDo - поиск по дате выполнен 2-мя методами:  1) хардкодным проставлением в БД поля - только день (без времени), см. пер-ную ниже
    //                                              2) запросом в БД от 00.00.00 текущей даты до 23.59.59
    // Как правильно?
    LocalDate departureDateOnly;

    @ManyToOne
    Plane plane;

    BigDecimal basePrice;


    public Long getId() {
        return id;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal price) {
        this.basePrice = price;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public ZonedDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(ZonedDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public ZonedDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(ZonedDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String ticketNumber) {
        this.flightNumber = ticketNumber;
    }

    public LocalDate getDepartureDateOnly() {
        return departureDateOnly;
    }

    public void setDepartureDateOnly(LocalDate departureDateOnly) {
        this.departureDateOnly = departureDateOnly;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departureCity=" + departureAirport.getCity() +
                ", arrivalCity=" + arrivalAirport.getCity() +
                ", basePrice=" + basePrice +
                '}';
    }
}
