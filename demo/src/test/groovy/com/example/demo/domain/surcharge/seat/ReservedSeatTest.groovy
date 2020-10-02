package com.example.demo.domain.surcharge.seat

import com.example.demo.component.domain.fare.Yen
import com.example.demo.component.domain.surcharge.seat.ReservedSeat
import com.example.demo.component.domain.surcharge.superexpress.Hikari
import com.example.demo.component.domain.surcharge.superexpress.Nozomi
import com.example.demo.component.domain.surcharge.superexpress.SuperExpress
import org.mockito.Mockito
import spock.lang.Specification
import spock.lang.Unroll

class ReservedSeatTest extends Specification {
    @Unroll
    def "#destination行きの指定席ひかり特急料金#value"() {
        when:
        def reservedSeat = new ReservedSeat(destination)

        then:
        reservedSeat.getSeatSurcharge().getValue() == value

        where:
        destination      || value
        Hikari.Himeji    || 5920
        Hikari.ShinOsaka || 5490
    }

    @Unroll
    def "#destination行きの指定席のぞみ特急料金#value"() {
        when:
        def reservedSeat = new ReservedSeat(destination)

        then:
        reservedSeat.getSeatSurcharge().getValue() == value

        where:
        destination      || value
        Nozomi.Himeji    || 5920 + 530
        Nozomi.ShinOsaka || 5490 + 320
    }

    def "getSuperExpressSurcharge()呼び出し確認テスト"() {
        given:
        SuperExpress superExpress = Mock(SuperExpress)
        def reservedSeat = new ReservedSeat(superExpress)

        when:
        reservedSeat.getSeatSurcharge()

        then:
        1 * superExpress.getSuperExpressSurcharge()

    }

    def "getSuperExpressSurcharge()mockitoによる呼び出し確認テスト"() {
        given:
        SuperExpress superExpress = Mockito.mock(SuperExpress)
        Mockito.when(superExpress.getSuperExpressSurcharge()).thenReturn(null)
        def reservedSeat = new ReservedSeat(superExpress)

        when:
        reservedSeat.getSeatSurcharge()

        then:
        Mockito.verify(superExpress, Mockito.times(1)).getSuperExpressSurcharge() == null

        where:
        value || result
        1     || 1
    }

    def "getSeatsSurcharge()確認テスト"() {
        given:
        SuperExpress superExpress = Mock(SuperExpress)
        def reservedSeat = new ReservedSeat(superExpress)
        superExpress.getSuperExpressSurcharge() >> new Yen(value)

        when:
        def response = reservedSeat.getSeatSurcharge()

        then:
        response.getValue() == result

        where:
        value || result
        2     || 2
        3     || 3
    }
}
