package com.example.demo.component.domain.discount;


import com.example.demo.component.domain.*;
import com.example.demo.component.domain.Number;
import com.example.demo.component.domain.surcharge.seat.Seat;
import com.example.demo.component.domain.fare.BasicFare;
import com.example.demo.component.domain.fare.FareType;
import com.example.demo.component.domain.fare.Yen;

public class RoundTripDiscount implements Discount {

    private static final RailwayDistance MIN_DISCOUNT_DISTANCE = new RailwayDistance(601);
    private static final DiscountRate ROUNDTRIP_DISCOUNT_RATE = new DiscountRate(0.1);
    private static final Number NUMBER_OF_SHINKANSEN_USE = new Number(2);

    private final PassengerNumber passengerNumber;
    private final Destination destination;

    private final DiscountRate roundTripDiscount;

    /*
    ・RailWayDistance(営業キロ)が601キロ未満ならば例外投げる
    ・往復選択されていなければ例外投げる
    ・割引率は1割
     */
    public RoundTripDiscount(
            PassengerNumber passengerNumber,
            Destination destination,
            OneWayOrRoundTrip oneWayOrRoundTrip
    ) {
        if (isLackOfDistance(destination)) {
            throw new RuntimeException("往復割引を適用できません。");
        }

        if (!isRoundTrip(oneWayOrRoundTrip)) {
            throw new RuntimeException("往復割引を適用できません。");
        }

        this.passengerNumber = passengerNumber;
        this.destination = destination;
        this.roundTripDiscount = ROUNDTRIP_DISCOUNT_RATE;

    }

    //RailWayDistance(営業キロ)が不足して入ればtrue
    public boolean isLackOfDistance(Destination destination){
        return destination.getRailwayDistance().compare(MIN_DISCOUNT_DISTANCE) < 0;
    }

    public boolean isRoundTrip(OneWayOrRoundTrip oneWayOrRoundTrip){
        return oneWayOrRoundTrip.getNumber().equals(NUMBER_OF_SHINKANSEN_USE);
    }

    @Override
    public DiscountRate getDiscountRate() {
        return this.roundTripDiscount;
    }

    @Override
    public Number getFreePassengerExcludedAdultNumber() {
        return this.passengerNumber.getAdultPassengerNumber();
    }

    @Override
    public Number getFreePassengerExcludedChildNumber() {
        return this.passengerNumber.getChildPassengerNumber();
    }


    public Yen fareCalculate(Seat seat) {
        FareType adultType = FareType.valueOf("Adult");
        FareType childType = FareType.valueOf("Child");

        return adultType.getBasicFare(BasicFare.valueOf(destination.toString()))
                .discount(roundTripDiscount)
                .roundDownLessThan10Yen()
                .add(adultType.getSurcharge(seat))
                .multiple(NUMBER_OF_SHINKANSEN_USE)
                .multiple(passengerNumber.getAdultPassengerNumber())
                .add(
                        childType.getBasicFare(BasicFare.valueOf(destination.toString()))
                                .discount(roundTripDiscount)
                                .roundDownLessThan10Yen()
                                .add(childType.getSurcharge(seat))
                                .multiple(NUMBER_OF_SHINKANSEN_USE)
                                .multiple(passengerNumber.getChildPassengerNumber())
                );
    }
}
