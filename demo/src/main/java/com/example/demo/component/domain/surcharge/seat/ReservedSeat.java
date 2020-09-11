package com.example.demo.component.domain.surcharge.seat;

import com.example.demo.component.domain.fare.Yen;
import com.example.demo.component.domain.surcharge.superexpress.SuperExpress;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReservedSeat implements Seat {

    private final SuperExpress surcharge;

    public Yen getSeatSurcharge() {
        return this.surcharge.getSuperExpressSurcharge();
    }
}
