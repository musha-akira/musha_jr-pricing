package com.example.demo.component.domain.fare;

import com.example.demo.component.domain.surcharge.seat.Seat;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FareType {
    Adult(new AdultFare()),
    Child(new ChildFare());

    private final Fare fare;

    public Yen getBasicFare(BasicFare basicFare) {
        return this.fare.getBasicFare(basicFare);
    }

    public Yen getSurcharge(Seat seat) {
        return this.fare.getSurcharge(seat);
    }
}
