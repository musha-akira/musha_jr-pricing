package com.example.demo.API;

import com.example.demo.domain.*;
import com.example.demo.domain.Number;
import com.example.demo.domain.surcharge.seat.SeatType;
import com.example.demo.domain.surcharge.superexpress.ShinkansenType;
import com.example.demo.domain.discount.DiscountType;
import lombok.Getter;

@Getter
public class ShinkansenFareRequest {
    private Date date;
    private Destination destination;
    private SeatType seatType;
    private Number adultPassengerNumber;
    private Number childPassengerNumber;
    private DiscountType discountType;
    private ShinkansenType shinkansenType;
    private OneWayOrRoundTrip oneWayOrRoundTrip;
}
