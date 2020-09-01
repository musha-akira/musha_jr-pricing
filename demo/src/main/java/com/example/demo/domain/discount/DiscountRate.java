package com.example.demo.domain.discount;

import lombok.Getter;

@Getter
public class DiscountRate {
    private final double value;

    public DiscountRate(double value) {
        if (value > 1 || value < 0) {
            throw new RuntimeException("不正な値です");
        }
        this.value = value;
    }

}
