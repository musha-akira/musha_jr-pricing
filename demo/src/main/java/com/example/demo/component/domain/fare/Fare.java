package com.example.demo.component.domain.fare;

import com.example.demo.component.domain.surcharge.seat.Seat;

public interface Fare {
    Yen getBasicFare(BasicFare basicFare);
    Yen getSurcharge(Seat seat);
}
