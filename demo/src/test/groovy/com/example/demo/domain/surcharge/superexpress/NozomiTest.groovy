package com.example.demo.domain.surcharge.superexpress

import com.example.demo.component.domain.surcharge.superexpress.Nozomi
import spock.lang.Specification
import spock.lang.Unroll

class NozomiTest extends Specification {
    @Unroll
    def "#destination行きの指定席のぞみ特急料金は#value"() {
        when:
        def nozomi = Nozomi.valueOf(destination)

        then:
        nozomi.getSuperExpressSurcharge().getValue() == value

        where:
        destination || value
        "ShinOsaka" || 5490 + 320
        "Himeji"    || 5920 + 530
    }
}
