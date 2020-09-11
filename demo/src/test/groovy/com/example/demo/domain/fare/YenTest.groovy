package com.example.demo.domain.fare

import com.example.demo.component.domain.Number
import com.example.demo.component.domain.discount.DiscountRate
import com.example.demo.component.domain.fare.Yen
import spock.lang.Specification
import spock.lang.Unroll

class YenTest extends Specification {
    def calc

    @Unroll
    def "値#valueの時のgetValueの値#result"() {
        when:
        calc = new Yen(value)

        then:
        calc.getValue() == result

        where:
        value                || result
        0                    || 0
        1                    || 1
        Double.MAX_VALUE * 2 || Double.MAX_VALUE * 2
    }

    @Unroll
    def "値#valueの時の例外は#result"() {
        when:
        calc = new Yen(value as double)

        then:
        Exception e = thrown()
        e.message == result

        where:
        value || result
        -0.1  || "値が適切ではありません。"
    }

    @Unroll
    def "値#aと値#bの足し算の結果は#result"() {
        when:
        def aYen = new Yen(a)
        def bYen = new Yen(b)

        then:
        aYen.add(bYen).getValue() == result

        where:
        a                | b                || result
        0                | 0                || 0
        0                | 1                || 1
        1                | 0                || 1
        1                | 1                || 2
        0.5              | 0.5              || 1
        Double.MAX_VALUE | Double.MAX_VALUE || Double.MAX_VALUE * 2
    }

    @Unroll
    def "値#aと値#bの引き算の結果は#result"() {
        when:
        def aYen = new Yen(a)
        def bYen = new Yen(b)

        then:
        aYen.subtract(bYen).getValue() == result

        where:
        a                | b                || result
        0                | 0                || 0
        1                | 1                || 0
        1                | 0                || 1
        1                | 0.5              || 0.5
        0.5              | 0.5              || 0
        Double.MAX_VALUE | Double.MAX_VALUE || 0
    }

    @Unroll
    def "値段#value円の割引率#rateの時の割引結果は#result"() {
        given:
        calc = new Yen(value)

        when:
        def discountRate = new DiscountRate(rate as double)

        then:
        calc.discount(discountRate).getValue() == result

        where:
        value            | rate || result
        0                | 0    || 0
        0                | 0.3  || 0
        1                | 0    || 1
        1                | 1    || 0
        1                | 0.3  || 0.7
        1000             | 0.3  || 700
        Double.MAX_VALUE | 0.5  || Double.MAX_VALUE / 2
        Double.MAX_VALUE | 1    || 0
    }

    @Unroll
    def "値段#value円の数#numberを掛けた時の割引結果は#result"() {
        given:
        calc = new Yen(value)

        when:
        def num = new Number(number)

        then:
        calc.multiple(num).getValue() == result

        where:
        value            | number            || result
        0                | 0                 || 0
        0                | 2                 || 0
        1                | 0                 || 0
        1                | 1                 || 1
        1                | 2                 || 2
        10.5             | 3                 || 31.5
        Double.MAX_VALUE | 2                 || Double.MAX_VALUE * 2
        100              | Integer.MAX_VALUE || 2147483647.0 * 100
    }

    @Unroll
    def "値段#value円の時に10円未満切り捨てた結果#result"() {
        when:
        calc = new Yen(value)

        then:
        calc.roundDownLessThan10Yen().getValue() == result

        where:
        value || result
        1005  || 1000
        1009  || 1000
        5555  || 5550

    }
}
