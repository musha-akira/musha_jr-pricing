package com.example.demo.component.domain.discount;


import com.example.demo.component.domain.Date;
import com.example.demo.component.domain.Destination;
import com.example.demo.component.domain.OneWayOrRoundTrip;
import com.example.demo.component.domain.PassengerNumber;
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
