package com.example.demo.domain.surcharge.seat;


import com.example.demo.domain.fare.Yen;
import com.example.demo.domain.surcharge.superexpress.Hikari;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FreeSeat implements Seat {
    private static final Yen FREE_SEAT_DISCOUNT = new Yen(530);

    private final Hikari hikari;

    public Yen getSeatSurcharge() {
        return hikari.getSuperExpressSurcharge().subtract(FREE_SEAT_DISCOUNT);
    }
}
