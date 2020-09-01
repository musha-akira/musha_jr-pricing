package com.example.demo.domain.surcharge.superexpress;

import com.example.demo.domain.fare.Yen;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Nozomi implements SuperExpress {
    ShinOsaka(Hikari.ShinOsaka.getSuperExpressSurcharge().add(new Yen(320))),
    Himeji(Hikari.Himeji.getSuperExpressSurcharge().add(new Yen(530)));

    private final Yen yen;

    public Yen getSuperExpressSurcharge(){
        return this.yen;
    }

}
