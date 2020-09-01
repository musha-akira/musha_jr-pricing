package com.example.demo.domain.discount;


import com.example.demo.domain.Date;
import com.example.demo.domain.Destination;
import com.example.demo.domain.OneWayOrRoundTrip;
import com.example.demo.domain.PassengerNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiscountOrder {
    private final PassengerNumber passengerNumber;
    private final Date date;
    private final Destination destination;
    private final OneWayOrRoundTrip oneWayOrRoundTrip;
}
