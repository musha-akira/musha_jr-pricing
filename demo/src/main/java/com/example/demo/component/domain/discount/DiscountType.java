package com.example.demo.component.domain.discount;

import com.example.demo.component.domain.Date;
import com.example.demo.component.domain.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DiscountType {
    RoundTrip{
        public Discount createDiscount(DiscountOrder order){
            PassengerNumber passengerNumber = order.getPassengerNumber();
            Destination destination = order.getDestination();
            OneWayOrRoundTrip oneWayOrRoundTrip = order.getOneWayOrRoundTrip();

            return new RoundTripDiscount(passengerNumber,destination,oneWayOrRoundTrip);
        }
    },
    Group{
        public Discount createDiscount(DiscountOrder order) {
            PassengerNumber passengerNumber = order.getPassengerNumber();
            Date date = order.getDate();

            return new GroupDiscount(passengerNumber, date);
        }
    },
    Non{
        public Discount createDiscount(DiscountOrder order) {
            PassengerNumber passengerNumber = order.getPassengerNumber();
            Destination destination = order.getDestination();

            return new NonDiscount(passengerNumber, destination);
        }
    };

    public abstract Discount createDiscount(DiscountOrder order);
}
