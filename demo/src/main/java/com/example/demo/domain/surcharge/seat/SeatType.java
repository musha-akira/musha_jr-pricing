package com.example.demo.domain.surcharge.seat;

import com.example.demo.domain.surcharge.superexpress.Hikari;
import com.example.demo.domain.surcharge.superexpress.SuperExpress;

public enum SeatType {
    FreeSeat{
        @Override
        public Seat createSeat(SuperExpress superExpress) {
            return new FreeSeat((Hikari) superExpress);
        }
    },
    ReservedSeat{
        @Override
        public Seat createSeat(SuperExpress superExpress){
            return new ReservedSeat(superExpress);
        }
    };

    public abstract Seat createSeat(SuperExpress superExpress);
}
