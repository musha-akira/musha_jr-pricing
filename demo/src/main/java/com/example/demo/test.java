package com.example.demo;

import com.example.demo.component.service.ShinkansenFareCalculationService;
import com.example.demo.component.domain.Date;
import com.example.demo.component.domain.Destination;
import com.example.demo.component.domain.Number;
import com.example.demo.component.domain.OneWayOrRoundTrip;
import com.example.demo.component.domain.discount.DiscountType;
import com.example.demo.component.domain.surcharge.seat.SeatType;
import com.example.demo.component.domain.surcharge.superexpress.ShinkansenType;

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
                service.shinkansenFareCalculate(
                        date,
                        seatType,
                        destination,
                        adultPassengerNumber,
                        childPassengerNumber,
                        discountType,
                        shinkansenType,
                        oneWayOrRoundTrip

                ).getValue());


        System.out.println(Integer.MAX_VALUE);

//        System.out.println(new ShinkansenFareRequest.AdultPassengerNumberForm(new Number(1)).getAdultPassengerNumber().getNumber());
//        ShinkansenFareRequest request = new ShinkansenFareRequest(
//                new ShinkansenFareRequest.DateForm(date),
//                new ShinkansenFareRequest.DestinationForm(destination),
//                new ShinkansenFareRequest.SeatTypeForm(seatType),
//                new ShinkansenFareRequest.AdultPassengerNumberForm(adultPassengerNumber),
//                new ShinkansenFareRequest.ChildPassengerNumberForm(childPassengerNumber),
//                new ShinkansenFareRequest.DiscountTypeForm(discountType),
//                new ShinkansenFareRequest.ShinkansenTypeForm(shinkansenType),
//                new ShinkansenFareRequest.OneWayOrRoundTripForm(oneWayOrRoundTrip)
//        );
//        System.out.println(request.getAdultPassengerNumber().getNumber());

        //ShinkansenFareCalculationAPI api = new ShinkansenFareCalculationAPI();

        //System.out.println(api.invoke(request));

//        Map<String,Object> res = new HashMap();
//        res.put("results",
//                new ShinkansenFareCalculationService().shinkansenFareCalculate(
//                        request.getDate(),
//                        request.getSeatType(),
//                        request.getDestination(),
//                        request.getAdultPassengerNumber(),
//                        request.getChildPassengerNumber(),
//                        request.getDiscountType(),
//                        request.getShinkansenType(),
//                        request.getOneWayOrRoundTrip()
//                ).getValue()
//        );
//
//        System.out.println(res.toString());



    }
}
