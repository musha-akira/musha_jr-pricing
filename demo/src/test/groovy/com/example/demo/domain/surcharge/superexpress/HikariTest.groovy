package com.example.demo.domain.surcharge.superexpress

import com.example.demo.component.domain.surcharge.superexpress.Hikari
import spock.lang.Specification
import spock.lang.Unroll

class HikariTest extends Specification {
    @Unroll
    def "#destination行きの指定席特急料金は#value"() {
        when:
        def hikari = Hikari.valueOf(destination)

        then:
        hikari.getSuperExpressSurcharge().getValue() == value

        where:
        destination || value
        "ShinOsaka" || 5490
        "Himeji"    || 5920
    }
}
