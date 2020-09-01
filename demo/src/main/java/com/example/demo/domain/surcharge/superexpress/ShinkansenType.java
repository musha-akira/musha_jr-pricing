package com.example.demo.domain.surcharge.superexpress;

import com.example.demo.domain.Destination;

public enum ShinkansenType {
    Hikari{
        @Override
        public SuperExpress createSuperExpress(Destination destination){
            return com.example.demo.domain.surcharge.superexpress.Hikari.valueOf(destination.toString());
        }
    },
    Nozomi{
        @Override
        public SuperExpress createSuperExpress(Destination destination) {
            return com.example.demo.domain.surcharge.superexpress.Nozomi.valueOf(destination.toString());
        }
    };

    public abstract SuperExpress createSuperExpress(Destination destination);
}
