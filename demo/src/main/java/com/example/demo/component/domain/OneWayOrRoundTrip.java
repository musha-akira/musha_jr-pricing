package com.example.demo.component.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OneWayOrRoundTrip {
    ONE_WAY(new Number(1)),
    ROUND_TRIP(new Number(2));

    private final Number number;
}
