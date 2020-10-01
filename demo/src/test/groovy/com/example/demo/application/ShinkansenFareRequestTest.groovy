package com.example.demo.application

import com.example.demo.component.application.ShinkansenFareRequest
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.Validation

class ShinkansenFareRequestTest extends Specification {
    @Unroll
    def "#value年を設定した時のgetYear()結果#result"() {
        when:
        def dateForm = new ShinkansenFareRequest.DateForm()
        dateForm.setYear(value)

        then:
        dateForm.getYear() == result

        where:
        value || result
        1     || 1
        10    || 10
        100   || 100
        1000  || 1000

    }

    @Unroll
    def "年#valueを設定した時のDateFormの違反項目数#size"() {
        setup:
        def validator = Validation.buildDefaultValidatorFactory().getValidator()
        def dateForm = new ShinkansenFareRequest.DateForm()
        dateForm.setYear(value)
        dateForm.setMonth(1)
        dateForm.setDay(1)
        Set<ConstraintViolation<ShinkansenFareRequest.DateForm>> constraintViolations = validator.validate(dateForm)

        expect:
        constraintViolations.size() == size

        where:
        value || size
        1899  || 1
        1900  || 0
        2000  || 0
        2100  || 0
        2101  || 1

    }

    @Unroll
    def "月#valueを設定した時のDateFormの違反項目数#size"() {
        setup:
        def validator = Validation.buildDefaultValidatorFactory().getValidator()
        def dateForm = new ShinkansenFareRequest.DateForm()
        dateForm.setYear(2000)
        dateForm.setMonth(value)
        dateForm.setDay(1)
        Set<ConstraintViolation<ShinkansenFareRequest.DateForm>> constraintViolations = validator.validate(dateForm)

        expect:
        constraintViolations.size() == size

        where:
        value || size
        0     || 1
        1     || 0
        12    || 0
        13    || 1

    }

    @Unroll
    def "日#valueを設定した時のDateFormの違反項目数#size"() {
        setup:
        def validator = Validation.buildDefaultValidatorFactory().getValidator()
        def dateForm = new ShinkansenFareRequest.DateForm()
        dateForm.setYear(2000)
        dateForm.setMonth(1)
        dateForm.setDay(value)
        Set<ConstraintViolation<ShinkansenFareRequest.DateForm>> constraintViolations = validator.validate(dateForm)

        expect:
        constraintViolations.size() == size

        where:
        value || size
        0     || 1
        1     || 0
        31    || 0
        32    || 1

    }

    @Unroll
    def "#destinationを設定した時のDestinationFormの入力チェック結果#size"() {
        setup:
        def validator = Validation.buildDefaultValidatorFactory().getValidator()
        def destinationForm = new ShinkansenFareRequest.DestinationForm()
        destinationForm.setDestination(destination)
        Set<ConstraintViolation<ShinkansenFareRequest.DestinationForm>> constraintViolations = validator.validate(destinationForm)


        expect:
        constraintViolations.size() == size

        where:
        destination    || size
        ""             || 2
        "ShinOsaka"    || 0
        "Himeji"       || 0
        "abcdefghij"   || 0
        "abcdefghijk"  || 1
        "abcdefghijkl" || 1

    }
}
