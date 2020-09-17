package com.example.demo.domain.discount

import com.example.demo.component.domain.Destination
import com.example.demo.component.domain.Number
import com.example.demo.component.domain.OneWayOrRoundTrip
import com.example.demo.component.domain.PassengerNumber
import com.example.demo.component.domain.discount.RoundTripDiscount
import spock.lang.Specification
import spock.lang.Unroll

class RoundTripDiscountTest extends Specification {

    @Unroll
    def "目的地#destinationの時の営業キロが不足しているかどうかの判定結果は#result"() {
        when:
        def roundTripDiscount = new RoundTripDiscount(
                new PassengerNumber(new Number(1), new Number(0)),
                Destination.Himeji,
                OneWayOrRoundTrip.ROUND_TRIP
        )

        then:
        roundTripDiscount.isLackOfDistance(destination) == result

        where:
        destination           || result
        Destination.Himeji    || false
        Destination.ShinOsaka || true
    }

    @Unroll
    def "片道・往復#oneWayOrRoundTripの時の往復かどうかの判定結果は#result"() {
        when:
        def roundTripDiscount = new RoundTripDiscount(
                new PassengerNumber(new Number(1), new Number(0)),
                Destination.Himeji,
                OneWayOrRoundTrip.ROUND_TRIP
        )

        then:
        roundTripDiscount.isRoundTrip(oneWayOrRoundTrip) == result

        where:
        oneWayOrRoundTrip            || result
        OneWayOrRoundTrip.ONE_WAY    || false
        OneWayOrRoundTrip.ROUND_TRIP || true
    }

    @Unroll
    def "片道・往復#oneWayOrRoundTripの時の例外は#result"() {
        when:
        new RoundTripDiscount(
                new PassengerNumber(new Number(1), new Number(0)),
                Destination.Himeji,
                oneWayOrRoundTrip
        )

        then:
        Exception e = thrown()
        e.message == result

        where:
        oneWayOrRoundTrip         || result
        OneWayOrRoundTrip.ONE_WAY || "往復割引を適用できません。"
    }

    @Unroll
    def "目的地#destinationの時の例外は#result"() {
        when:
        new RoundTripDiscount(
                new PassengerNumber(new Number(1), new Number(0)),
                destination,
                OneWayOrRoundTrip.ROUND_TRIP
        )

        then:
        Exception e = thrown()
        e.message == result

        where:
        destination           || result
        Destination.ShinOsaka || "往復割引を適用できません。"
    }

    @Unroll
    def "目的地#destinationの時の割引率は#result"() {
        when:
        def roundTripDiscount = new RoundTripDiscount(
                new PassengerNumber(new Number(1), new Number(0)),
                destination,
                OneWayOrRoundTrip.ROUND_TRIP
        )

        then:
        roundTripDiscount.getDiscountRate().getValue() == result

        where:
        destination        || result
        Destination.Himeji || 0.1
    }

    @Unroll
    def "大人#adult人子供#child人の時の計算人数は大人#calcAdult人子供#calcChild人"() {
        when:
        def roundTripDiscount = new RoundTripDiscount(
                new PassengerNumber(new Number(adult), new Number(child)),
                Destination.Himeji,
                OneWayOrRoundTrip.ROUND_TRIP
        )

        then:
        roundTripDiscount.getCalculateAdultNumber().getNumber() == calcAdult
        roundTripDiscount.getCalculateChildNumber().getNumber() == calcChild

        where:
        adult | child || calcAdult | calcChild
        1     | 0     || 1         | 0
        0     | 1     || 0         | 1
        1     | 1     || 1         | 1
    }
}
