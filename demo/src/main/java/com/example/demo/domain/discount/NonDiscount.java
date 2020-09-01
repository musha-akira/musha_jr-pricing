package com.example.demo.domain.discount;

import com.example.demo.domain.Destination;
import com.example.demo.domain.Number;
import com.example.demo.domain.PassengerNumber;
import com.example.demo.domain.surcharge.seat.Seat;
import com.example.demo.domain.fare.BasicFare;
import com.example.demo.domain.fare.FareType;
import com.example.demo.domain.fare.Yen;

public class NonDiscount implements Discount {
    private final PassengerNumber passengerNumber;
    private final Destination destination;

    private final DiscountRate discountRate;

    /*
    ・割引率0
     */
    public NonDiscount(PassengerNumber passengerNumber, Destination destination) {
        this.passengerNumber = passengerNumber;

        this.destination = destination;

        this.discountRate = new DiscountRate(0);
    }

    @Override
    public DiscountRate getDiscountRate() {
        return this.discountRate;
    }

    @Override
    public Number getCalculateAdultNumber() {
        return this.passengerNumber.getAdultPassengerNumber();
    }

    @Override
    public Number getCalculateChildNumber() {
        return this.passengerNumber.getChildPassengerNumber();
    }

    public Yen fareCalculate(Seat seat) {

        FareType adultType = FareType.valueOf("Adult");
        FareType childType = FareType.valueOf("Child");

        return adultType.getBasicFare(BasicFare.valueOf(this.destination.toString()))
                .discount(this.discountRate)
                .roundDownLessThan10Yen()
                .add(adultType.getSurcharge(seat))
                .multiple(this.passengerNumber.getAdultPassengerNumber())
                .add(
                        childType.getBasicFare(BasicFare.valueOf(this.destination.toString()))
                                .discount(this.discountRate)
                                .roundDownLessThan10Yen()
                                .add(childType.getSurcharge(seat))
                                .multiple(this.passengerNumber.getChildPassengerNumber())
                );
    }
}
