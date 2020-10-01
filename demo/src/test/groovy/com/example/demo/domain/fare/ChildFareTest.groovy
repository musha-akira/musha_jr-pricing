package com.example.demo.domain.fare

import com.example.demo.component.domain.fare.BasicFare
import com.example.demo.component.domain.fare.ChildFare
import com.example.demo.component.domain.surcharge.seat.FreeSeat
import com.example.demo.component.domain.surcharge.seat.ReservedSeat
import com.example.demo.component.domain.surcharge.superexpress.Hikari
import spock.lang.Specification
import spock.lang.Unroll

class ChildFareTest extends Specification {
    @Unroll
    def "#destination行きの子供運賃は#value"() {
        when:
        def childFare = new ChildFare()

        then:
        childFare.getBasicFare(destination).getValue() == value

        where:
        destination         || value
        BasicFare.ShinOsaka || 4450
        BasicFare.Himeji    || 5000
    }

    @Unroll
    def "#destination行きの自由席子供特急料金は#value"() {
        when:
        def childFare = new ChildFare()

        then:
        childFare.getSurcharge(new FreeSeat(destination)).getValue() == value

        where:
        destination      || value
        Hikari.ShinOsaka || 2480
        Hikari.Himeji    || 2690
    }

    @Unroll
    def "#destination行きの指定席子供特急料金は#value"() {
        when:
        def childFare = new ChildFare()

        then:
        childFare.getSurcharge(new ReservedSeat(destination)).getValue() == value

        where:
        destination      || value
        Hikari.ShinOsaka || 2740
        Hikari.Himeji    || 2960
    }
}
