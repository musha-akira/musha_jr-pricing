package com.example.demo.component.domain;

import lombok.Getter;

@Getter
public class RailwayDistance {
    private final int distance;

    public int compare(RailwayDistance railwayDistance) {
        return Integer.compare(this.distance, railwayDistance.getDistance());
    }

    public RailwayDistance(int distance) {
        if (distance < 0) {
            throw new RuntimeException("距離が適切ではありません。");
        }
        this.distance = distance;
    }
}
