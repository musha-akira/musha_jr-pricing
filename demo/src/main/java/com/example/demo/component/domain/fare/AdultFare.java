package com.example.demo.component.domain.fare;

import com.example.demo.component.domain.surcharge.seat.Seat;

public class AdultFare implements Fare {

    public Yen getBasicFare(BasicFare basicFare){
        return basicFare.getYen();
    }

    public Yen getSurcharge(Seat seat){
        return seat.getSeatSurcharge();
    }
}
