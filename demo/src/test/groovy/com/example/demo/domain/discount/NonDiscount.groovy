package com.example.demo.domain.discount

import com.example.demo.component.domain.Destination
import com.example.demo.component.domain.Number
import com.example.demo.component.domain.PassengerNumber
import spock.lang.Specification
import spock.lang.Unroll

class NonDiscount extends Specification {
    @Unroll
    def "目的地#destinationの時の割引率は#result"() {
        when:
        def nonDiscount = new com.example.demo.component.domain.discount.NonDiscount(
                new PassengerNumber(new Number(1), new Number(0)),
                destination
        )

        then:
        nonDiscount.getDiscountRate().getValue() == result

        where:
        destination           || result
        Destination.ShinOsaka || 0.0
        Destination.Himeji    || 0.0
    }

    @Unroll
    def "大人#adult人子供#child人の時の計算人数は大人#calcAdult人子供#calcChild人"() {
        when:
        def nonDiscount = new com.example.demo.component.domain.discount.NonDiscount(
                new PassengerNumber(new Number(adult), new Number(child)),
                Destination.Himeji
        )

        then:
        nonDiscount.getCalculateAdultNumber().getNumber() == calcAdult
        nonDiscount.getCalculateChildNumber().getNumber() == calcChild

        where:
        adult | child || calcAdult | calcChild
        1     | 0     || 1         | 0
        0     | 1     || 0         | 1
        1     | 1     || 1         | 1
    }
}
