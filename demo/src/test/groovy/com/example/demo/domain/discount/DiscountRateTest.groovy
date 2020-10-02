package com.example.demo.domain.discount


import com.example.demo.component.domain.discount.DiscountRate
import spock.lang.Specification
import spock.lang.Unroll


class DiscountRateTest extends Specification {
    @Unroll
    def "割引率#valueの時の割引率の値#result"() {
        when:
        def rate = new DiscountRate(value as double)

        then:
        rate.getValue() == result

        where:
        value || result
        0     || 0
        1     || 1
        0.5   || 0.5
    }

    @Unroll
    def "割引率#valueを入力した時の例外は#result"() {
        when:
        new DiscountRate(value as double)

        then:
        Exception e = thrown()
        e.message == result

        where:
        value || result
        -0.1  || "不正な値です"
        1.1   || "不正な値です"
    }
}
