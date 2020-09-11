package com.example.demo.component.domain.surcharge.superexpress;

import com.example.demo.component.domain.Destination;

public enum ShinkansenType {
    Hikari{
        @Override
        public SuperExpress createSuperExpress(Destination destination){
            return com.example.demo.component.domain.surcharge.superexpress.Hikari.valueOf(destination.toString());
        }
    },
    Nozomi{
        @Override
        public SuperExpress createSuperExpress(Destination destination) {
            return com.example.demo.component.domain.surcharge.superexpress.Nozomi.valueOf(destination.toString());
        }
    };

    public abstract SuperExpress createSuperExpress(Destination destination);
}
