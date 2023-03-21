package com.example.airline.model;

import com.example.airline.model.enumerations.PlaneStatusType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;

    @ManyToOne
    private PlaneModel planeModel;

    @Enumerated(EnumType.STRING)
    private PlaneStatusType status;

    @ManyToOne
    private Airport airport;

    @ManyToOne
    private GroundService groundService;


    public Long getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public PlaneStatusType getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = PlaneStatusType.valueOf(status);
    }

    public GroundService getGroundService() {
        return groundService;
    }

    public void setGroundService(GroundService groundService) {
        this.groundService = groundService;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(PlaneModel planeModel) {
        this.planeModel = planeModel;
    }
}
