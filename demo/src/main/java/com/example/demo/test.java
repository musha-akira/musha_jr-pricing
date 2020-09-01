package com.example.demo;

import com.example.demo.domain.Date;
import com.example.demo.domain.Destination;
import com.example.demo.domain.Number;
import com.example.demo.domain.OneWayOrRoundTrip;
import com.example.demo.domain.surcharge.seat.SeatType;
import com.example.demo.domain.surcharge.superexpress.ShinkansenType;
import com.example.demo.domain.discount.DiscountType;
import com.example.demo.service.ShinkansenFareCalculationService;

public class test {
    public static void main(String[] args) {

        //リクエスト
        Date date = new Date(2020, 8, 10);
        Destination destination = Destination.ShinOsaka;
        SeatType seatType = SeatType.FreeSeat;
        ShinkansenType shinkansenType = ShinkansenType.Hikari;
        Number adultPassengerNumber = new Number(10);
        Number childPassengerNumber = new Number(21);
        DiscountType discountType = DiscountType.Group;
        OneWayOrRoundTrip oneWayOrRoundTrip = OneWayOrRoundTrip.ONE_WAY;


        ShinkansenFareCalculationService service = new ShinkansenFareCalculationService();

        System.out.println(
                service.ShinkansenFareCalculate(
                        date,
                        seatType,
                        destination,
                        adultPassengerNumber,
                        childPassengerNumber,
                        discountType,
                        shinkansenType,
                        oneWayOrRoundTrip

                ).getValue());


    }
}
