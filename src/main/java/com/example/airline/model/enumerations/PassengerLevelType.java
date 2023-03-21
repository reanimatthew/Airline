package com.example.airline.model.enumerations;

import java.math.BigDecimal;

public enum PassengerLevelType {
    PLATINUM("0.8"),
    GOLD("0.85"),
    SILVER("0.9"),
    STANDARD("1");

    private BigDecimal discount;

    PassengerLevelType(String discount) {
        this.discount = new BigDecimal(discount);
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
