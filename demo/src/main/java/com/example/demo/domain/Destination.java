package com.example.demo.domain;

import com.example.demo.domain.fare.BasicFare;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Destination {

    ShinOsaka(new RailwayDistance(553),BasicFare.ShinOsaka), Himeji(new RailwayDistance(644),BasicFare.Himeji);

    private final RailwayDistance railwayDistance;
    private final BasicFare basicFare;
    //private final SuperExpressSurcharge superExpressSurcharge;
}