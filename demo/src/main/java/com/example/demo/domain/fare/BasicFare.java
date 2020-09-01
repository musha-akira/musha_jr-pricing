package com.example.demo.domain.fare;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BasicFare {
    ShinOsaka(new Yen(8910)), Himeji(new Yen(10010));

    private final Yen yen;
}
