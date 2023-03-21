package com.example.airline.model.enumerations;

import java.math.BigDecimal;

public enum TicketType {
    FIRST("2"),
    BUSINESS("1.5"),
    ECONOMY("1");

    private final BigDecimal extraCharge;

    TicketType(String extraCharge) {
        this.extraCharge = new BigDecimal(extraCharge);
    }

    public BigDecimal getExtraCharge() {
        return extraCharge;
    }
}
