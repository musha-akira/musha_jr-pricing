package com.example.demo.domain.surcharge.superexpress;

import com.example.demo.domain.fare.Yen;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Hikari implements SuperExpress {

    ShinOsaka(new Yen(5490)),Himeji(new Yen(5920));

    private final Yen yen;

    public Yen getSuperExpressSurcharge(){
        return this.yen;
    }
}
