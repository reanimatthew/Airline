package com.example.airline.model;

import com.example.airline.model.enumerations.GroundServiceType;
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
public class GroundService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Airport airport;

    @Enumerated(EnumType.STRING)
    private GroundServiceType groundServiceType;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public GroundServiceType getGroundServiceType() {
        return groundServiceType;
    }

    public void setGroundServiceType(String groundServiceType) {
        this.groundServiceType = GroundServiceType.valueOf(groundServiceType);
    }
}
