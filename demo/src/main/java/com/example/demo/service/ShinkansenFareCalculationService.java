package com.example.demo.service;

import com.example.demo.domain.Number;
import com.example.demo.domain.*;
import com.example.demo.domain.surcharge.seat.Seat;
import com.example.demo.domain.surcharge.seat.SeatType;
import com.example.demo.domain.surcharge.superexpress.ShinkansenType;
import com.example.demo.domain.discount.Discount;
import com.example.demo.domain.discount.DiscountOrder;
import com.example.demo.domain.discount.DiscountType;
import com.example.demo.domain.fare.FareType;
import com.example.demo.domain.fare.Yen;
import org.springframework.stereotype.Service;

@Service
public class ShinkansenFareCalculationService {

    public Yen ShinkansenFareCalculate(
            Date date,
            SeatType seatType,
            Destination destination,
            Number adultPassengerNumber,
            Number childPassengerNumber,
            DiscountType discountType,
            ShinkansenType shinkansenType,
            OneWayOrRoundTrip oneWayOrRoundTrip
    ) {
        Seat seat = seatType.createSeat(shinkansenType.createSuperExpress(destination));
        PassengerNumber passengerNumber = new PassengerNumber(adultPassengerNumber, childPassengerNumber);
        DiscountOrder discountOrder = new DiscountOrder(passengerNumber, date, destination, oneWayOrRoundTrip);
        Discount discount = discountType.createDiscount(discountOrder);

        FareType adultType = FareType.valueOf("Adult");
        FareType childType = FareType.valueOf("Child");

        Yen adultFare = shinkansenFareCalculateForOnePerson
                (
                        adultType,
                        destination,
                        discount,
                        seat,
                        oneWayOrRoundTrip
                );

        Yen childFare = shinkansenFareCalculateForOnePerson
                (
                        childType,
                        destination,
                        discount,
                        seat,
                        oneWayOrRoundTrip
                );

        Number adultCalculatePassengerNumber = discount.getCalculateAdultNumber();
        Number childCalculatePassengerNumber = discount.getCalculateChildNumber();

        return adultFare
                .multiple(adultCalculatePassengerNumber)
                .add(
                        childFare
                                .multiple(childCalculatePassengerNumber)
                );


    }

    public Yen shinkansenFareCalculateForOnePerson(
            FareType fareType,
            Destination destination,
            Discount discount,
            Seat seat,
            OneWayOrRoundTrip oneWayOrRoundTrip
    ) {
        return fareType.getBasicFare(destination.getBasicFare())
                .discount(discount.getDiscountRate())
                .roundDownLessThan10Yen()
                .add(fareType.getSurcharge(seat))
                .multiple(oneWayOrRoundTrip.getNumber());
    }
}
