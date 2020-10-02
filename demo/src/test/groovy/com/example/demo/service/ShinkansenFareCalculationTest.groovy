package com.example.demo.service

import com.example.demo.component.domain.Date
import com.example.demo.component.domain.Destination
import com.example.demo.component.domain.Number
import com.example.demo.component.domain.OneWayOrRoundTrip
import com.example.demo.component.domain.discount.DiscountType
import com.example.demo.component.domain.surcharge.seat.SeatType
import com.example.demo.component.domain.surcharge.superexpress.ShinkansenType
import com.example.demo.component.service.ShinkansenFareCalculationService
import spock.lang.Specification
import spock.lang.Unroll

class ShinkansenFareCalculationTest extends Specification {
    def fareCalc

    def setup() {
        fareCalc = new ShinkansenFareCalculationService()
    }

    def cleanup() {
        fareCalc = null
    }

    @Unroll
    def "fareCalculate"() {
        expect:
        fareCalc.shinkansenFareCalculate(
                date,
                seatType,
                destination,
                adultPassengerNumber,
                childPassengerNumber,
                discountType,
                shinkansenType,
                oneWayOrRoundTrip
        ).getValue() == result

        where:
        date                  | seatType              | destination           | adultPassengerNumber | childPassengerNumber | discountType           | shinkansenType        | oneWayOrRoundTrip            | result
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 13870
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(0)        | new Number(1)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 6930
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(0)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 0
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.Himeji    | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 15400
        new Date(2020, 8, 10) | SeatType.ReservedSeat | Destination.ShinOsaka | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 14400
        new Date(2020, 8, 10) | SeatType.ReservedSeat | Destination.ShinOsaka | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Nozomi | OneWayOrRoundTrip.ONE_WAY    | 14720
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 27740
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.Himeji    | new Number(1)        | new Number(0)        | DiscountType.RoundTrip | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 28780
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(2)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 27740
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(8)        | new Number(0)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 100240
        new Date(2020, 1, 1)  | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(8)        | new Number(0)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 103760
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(30)       | new Number(0)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 375900
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(31)       | new Number(0)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 375900
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(50)       | new Number(0)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 613970
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(51)       | new Number(0)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 613970
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(10)       | new Number(21)       | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 244230
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(1)        | new Number(50)       | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 306740


    }

    @Unroll
    def "例外発生テスト"() {
        when:
        fareCalc.shinkansenFareCalculate(
                date,
                seatType,
                destination,
                adultPassengerNumber,
                childPassengerNumber,
                discountType,
                shinkansenType,
                oneWayOrRoundTrip
        ).getValue()

        then:
        Exception e = thrown()
        e.message == result

        where:
        date                  | seatType          | destination           | adultPassengerNumber | childPassengerNumber | discountType           | shinkansenType        | oneWayOrRoundTrip            | result
        new Date(2020, 8, 10) | SeatType.FreeSeat | Destination.ShinOsaka | new Number(1)        | new Number(0)        | DiscountType.RoundTrip | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | "往復割引を適用できません。"
        new Date(2020, 8, 10) | SeatType.FreeSeat | Destination.Himeji    | new Number(7)        | new Number(0)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | "団体割引を適用できません。"
        new Date(2020, 8, 10) | SeatType.FreeSeat | Destination.Himeji    | new Number(1)        | new Number(0)        | DiscountType.RoundTrip | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | "往復割引を適用できません。"
        new Date(2020, 8, 10) | SeatType.FreeSeat | Destination.Himeji    | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Nozomi | OneWayOrRoundTrip.ONE_WAY    | "class com.example.demo.component.domain.surcharge.superexpress.Nozomi cannot be cast to class com.example.demo.component.domain.surcharge.superexpress.Hikari (com.example.demo.component.domain.surcharge.superexpress.Nozomi and com.example.demo.component.domain.surcharge.superexpress.Hikari are in unnamed module of loader 'app')"


    }

    @Unroll
    def "pict fareCalculate test"() {
        expect:
        fareCalc.shinkansenFareCalculate(
                date,
                seatType,
                destination,
                adultPassengerNumber,
                childPassengerNumber,
                discountType,
                shinkansenType,
                oneWayOrRoundTrip
        ).getValue() == result

        where:
        date                  | seatType              | destination           | adultPassengerNumber | childPassengerNumber | discountType           | shinkansenType        | oneWayOrRoundTrip            | result
        new Date(2020, 1, 1)  | SeatType.ReservedSeat | Destination.Himeji    | new Number(1)        | new Number(1)        | DiscountType.RoundTrip | ShinkansenType.Nozomi | OneWayOrRoundTrip.ROUND_TRIP | 15450 * 2 + 7720 * 2
        new Date(2020, 1, 1)  | SeatType.ReservedSeat | Destination.Himeji    | new Number(0)        | new Number(1)        | DiscountType.Non       | ShinkansenType.Nozomi | OneWayOrRoundTrip.ONE_WAY    | 8220
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.Himeji    | new Number(8)        | new Number(0)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 111120 * 2
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.Himeji    | new Number(0)        | new Number(1)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 7690 * 2
        new Date(2020, 8, 10) | SeatType.ReservedSeat | Destination.Himeji    | new Number(1)        | new Number(0)        | DiscountType.RoundTrip | ShinkansenType.Nozomi | OneWayOrRoundTrip.ROUND_TRIP | 15450 * 2
        new Date(2020, 1, 1)  | SeatType.FreeSeat     | Destination.Himeji    | new Number(1)        | new Number(1)        | DiscountType.RoundTrip | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 14390 * 2 + 7190 * 2
        new Date(2020, 1, 1)  | SeatType.ReservedSeat | Destination.Himeji    | new Number(0)        | new Number(1)        | DiscountType.RoundTrip | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 7460 * 2
        new Date(2020, 1, 1)  | SeatType.ReservedSeat | Destination.Himeji    | new Number(0)        | new Number(8)        | DiscountType.Group     | ShinkansenType.Nozomi | OneWayOrRoundTrip.ROUND_TRIP | 7720 * 2 * 8
        new Date(2020, 1, 1)  | SeatType.FreeSeat     | Destination.Himeji    | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 15400
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.Himeji    | new Number(0)        | new Number(1)        | DiscountType.RoundTrip | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 7190 * 2
        new Date(2020, 1, 1)  | SeatType.FreeSeat     | Destination.Himeji    | new Number(1)        | new Number(0)        | DiscountType.RoundTrip | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 14390 * 2
        new Date(2020, 8, 10) | SeatType.ReservedSeat | Destination.Himeji    | new Number(0)        | new Number(8)        | DiscountType.Group     | ShinkansenType.Nozomi | OneWayOrRoundTrip.ONE_WAY    | 7470 * 8
        new Date(2020, 1, 1)  | SeatType.ReservedSeat | Destination.Himeji    | new Number(1)        | new Number(1)        | DiscountType.Non       | ShinkansenType.Nozomi | OneWayOrRoundTrip.ROUND_TRIP | 16460 * 2 + 8220 * 2
        new Date(2020, 1, 1)  | SeatType.ReservedSeat | Destination.Himeji    | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 15930 * 2
        new Date(2020, 1, 1)  | SeatType.FreeSeat     | Destination.Himeji    | new Number(0)        | new Number(8)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ROUND_TRIP | 7190 * 2 * 8
        new Date(2020, 1, 1)  | SeatType.ReservedSeat | Destination.Himeji    | new Number(0)        | new Number(1)        | DiscountType.RoundTrip | ShinkansenType.Nozomi | OneWayOrRoundTrip.ROUND_TRIP | 7720 * 2
        new Date(2020, 8, 10) | SeatType.ReservedSeat | Destination.ShinOsaka | new Number(0)        | new Number(8)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 6520 * 8
        new Date(2020, 8, 10) | SeatType.ReservedSeat | Destination.ShinOsaka | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Nozomi | OneWayOrRoundTrip.ONE_WAY    | 14720
        new Date(2020, 1, 1)  | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(0)        | new Number(1)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 6930
        new Date(2020, 1, 1)  | SeatType.ReservedSeat | Destination.ShinOsaka | new Number(8)        | new Number(0)        | DiscountType.Group     | ShinkansenType.Nozomi | OneWayOrRoundTrip.ONE_WAY    | 13820 * 8
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(4)        | new Number(4)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 12530 * 4 + 6260 * 4
        new Date(2020, 1, 1)  | SeatType.ReservedSeat | Destination.ShinOsaka | new Number(8)        | new Number(0)        | DiscountType.Group     | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 13500 * 8
        new Date(2020, 8, 10) | SeatType.ReservedSeat | Destination.ShinOsaka | new Number(0)        | new Number(1)        | DiscountType.Non       | ShinkansenType.Nozomi | OneWayOrRoundTrip.ONE_WAY    | 7350
        new Date(2020, 8, 10) | SeatType.FreeSeat     | Destination.ShinOsaka | new Number(1)        | new Number(0)        | DiscountType.Non       | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY    | 13870


    }
}
