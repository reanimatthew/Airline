package com.example.airline.model.enumerations;

import java.math.BigDecimal;

public enum PositionType {
    CAPTAIN("30000"),
    FIRST_OFFICER("25000"),
    PURSER("20000"),
    FLIGHT_ATTENDANT("15000"),
    FLIGHT_MEDIC("20000");

    private final BigDecimal baseSalary;

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    PositionType(String baseSalary) {
        this.baseSalary = new BigDecimal(baseSalary);
    }
}
