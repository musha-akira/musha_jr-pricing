package com.example.demo.component.domain.fare;

import com.example.demo.component.domain.Number;
import com.example.demo.component.domain.discount.DiscountRate;
import lombok.Getter;

@Getter
public class Yen {
    private final double value;

    public Yen(double value) {
        if (value < 0) {
            throw new RuntimeException("値が適切ではありません。");
        }
        this.value = value;
    }

    public Yen add(Yen yen) {
        return new Yen(this.value + yen.getValue());
    }

    public Yen subtract(Yen yen) {
        return new Yen(this.value - yen.getValue());
    }

    public Yen discount(DiscountRate rate) {
        return new Yen(this.value * (1 - rate.getValue()));
    }

    public Yen multiple(Number number) {
        return new Yen(this.value * number.getNumber());
    }

    public Yen roundDownLessThan10Yen() {
        double onePlace = this.value / 10;
        return new Yen(Math.floor(onePlace) * 10);
    }
}