package com.example.demo.domain.surcharge.seat


import com.example.demo.component.domain.surcharge.seat.FreeSeat
import com.example.demo.component.domain.surcharge.superexpress.Hikari
import spock.lang.Specification
import spock.lang.Unroll

class FreeSeatTest extends Specification {
    @Unroll
    def "#destination行きの自由席特急料金#value"() {
        when:
        def freeSeat = new FreeSeat(destination)

        then:
        freeSeat.getSeatSurcharge().getValue() == value

        where:
        destination      || value
        Hikari.Himeji    || 5920 - 530
        Hikari.ShinOsaka || 5490 - 530
    }
}
