package com.detorresrc.foodorderingsystem.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);
    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Boolean isGreaterThanZero() {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    public Boolean isGreaterThan(Money other) {
        return this.value != null && this.value.compareTo(other.getValue()) > 0;
    }

    public Money add(Money other) {
        return new Money(setScale(this.value.add(other.getValue())));
    }

    public Money subtract(Money other) {
        return new Money(setScale(this.value.subtract(other.getValue())));
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(setScale(this.value.multiply(multiplier)));
    }

    public Money multiply(Integer multiplier) {
        return new Money(setScale(this.value.multiply(new BigDecimal(multiplier))));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    private BigDecimal setScale(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }
}
