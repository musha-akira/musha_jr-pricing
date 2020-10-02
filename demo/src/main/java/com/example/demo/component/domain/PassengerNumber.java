package com.example.demo.component.domain;

import lombok.Getter;

@Getter
public class PassengerNumber {
    private final Number adultPassengerNumber;
    private final Number childPassengerNumber;
    private final Number totalPassengerNumber;

    public PassengerNumber(Number adultPassengerNumber, Number childPassengerNumber) {
        this.adultPassengerNumber = adultPassengerNumber;
        this.childPassengerNumber = childPassengerNumber;
        this.totalPassengerNumber = adultPassengerNumber.add(childPassengerNumber);
    }


}
