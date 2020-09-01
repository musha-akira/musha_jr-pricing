package com.example.demo.domain.fare;

import com.example.demo.domain.surcharge.seat.Seat;

public interface Fare {
    Yen getBasicFare(BasicFare basicFare);
    Yen getSurcharge(Seat seat);
}
