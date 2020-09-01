package com.example.demo.domain.fare;

import com.example.demo.domain.Number;
import com.example.demo.domain.discount.DiscountRate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Yen {
    private final double value;

    public Yen add(Yen yen) {
        return new Yen(this.value + yen.getValue());
    }

    public Yen subtract(Yen yen){
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
        return new Yen(Math.floor(onePlace)*10);
    }
}