package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RailwayDistance {
    private final int distance;

    public int compare(RailwayDistance railwayDistance){
        return Integer.compare(this.distance, railwayDistance.getDistance());
    }
}
