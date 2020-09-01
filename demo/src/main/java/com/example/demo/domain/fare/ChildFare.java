package com.example.demo.domain.fare;

import com.example.demo.domain.discount.DiscountRate;
import com.example.demo.domain.surcharge.seat.Seat;

public class ChildFare implements Fare {

    private static final DiscountRate CHILD_DISCOUNT_RATE = new DiscountRate(0.5);

    public Yen getBasicFare(BasicFare basicFare){
        return basicFare.getYen()
                .discount(CHILD_DISCOUNT_RATE)
                .roundDownLessThan10Yen();
    }

    public Yen getSurcharge(Seat seat){
        return seat.getSeatSurcharge()
                .discount(CHILD_DISCOUNT_RATE)
                .roundDownLessThan10Yen();
    }
}