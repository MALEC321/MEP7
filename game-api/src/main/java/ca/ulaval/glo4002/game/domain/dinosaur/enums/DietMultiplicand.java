package ca.ulaval.glo4002.game.domain.dinosaur.enums;

import java.math.BigDecimal;

public enum DietMultiplicand {
    BURGER(new BigDecimal("0.2")),
    SALAD(new BigDecimal("0.5")),
    WATER(new BigDecimal("0.6"));

    private final BigDecimal multiplicand;

    DietMultiplicand(BigDecimal multiplicand) {
        this.multiplicand = multiplicand;
    }

    public BigDecimal getMultiplicand() {
        return multiplicand;
    }
}
