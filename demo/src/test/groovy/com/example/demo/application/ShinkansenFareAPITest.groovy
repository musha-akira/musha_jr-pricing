package com.example.demo.application

import com.example.demo.component.application.ShinkansenFareCalculationAPI
import com.example.demo.component.application.ShinkansenFareRequest
import com.example.demo.component.domain.Date
import com.example.demo.component.domain.Destination
import com.example.demo.component.domain.Number
import com.example.demo.component.domain.OneWayOrRoundTrip
import com.example.demo.component.domain.discount.DiscountType
import com.example.demo.component.domain.surcharge.seat.SeatType
import com.example.demo.component.domain.surcharge.superexpress.ShinkansenType
import com.example.demo.component.service.ShinkansenFareCalculationService
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class ShinkansenFareAPITest extends Specification {
    def "api test invoke"() {
        given:
        def target = new ShinkansenFareCalculationAPI()
        target.service = new ShinkansenFareCalculationService()
        target.request = Mock(ShinkansenFareRequest)
        target.request.getDate() >> date
        target.request.getDestination() >> destination
        target.request.getSeatType() >> seatType
        target.request.getAdultPassengerNumber() >> adultPassengerNumber
        target.request.getChildPassengerNumber() >> childPassengerNumber
        target.request.getDiscountType() >> discountType
        target.request.getShinkansenType() >> shinkansenType
        target.request.getOneWayOrRoundTrip() >> oneWayOrRoundTrip
        def mockMvc = MockMvcBuilders.standaloneSetup(target).build()
        def getRequest =
                MockMvcRequestBuilders.get("/jr-pricing")

        when:
        def response = mockMvc.perform(getRequest)

        then:
        def actual = response.andReturn().getResponse().getContentAsString()
        assert actual == expectedResponseBody

        where:

        date                  | seatType          | destination           | adultPassengerNumber | childPassengerNumber | discountType       | shinkansenType        | oneWayOrRoundTrip         || expectedResponseBody
        new Date(2020, 8, 10) | SeatType.FreeSeat | Destination.ShinOsaka | new Number(10)       | new Number(21)       | DiscountType.Group | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY || '{"results":244230.0}'

    }

//    def "method test invoke"() {
//        given:
//        def target = new ShinkansenFareCalculationAPI()
//        def request = new ShinkansenFareRequest(
//                new ShinkansenFareRequest.DateForm(date),
//                new ShinkansenFareRequest.DestinationForm(destination),
//                new ShinkansenFareRequest.SeatTypeForm(seatType),
//                new ShinkansenFareRequest.AdultPassengerNumberForm(adultPassengerNumber),
//                new ShinkansenFareRequest.ChildPassengerNumberForm(childPassengerNumber),
//                new ShinkansenFareRequest.DiscountTypeForm(discountType),
//                new ShinkansenFareRequest.ShinkansenTypeForm(shinkansenType),
//                new ShinkansenFareRequest.OneWayOrRoundTripForm(oneWayOrRoundTrip)
//        )
//        target.service = Mock(ShinkansenFareCalculationService)
//        target.service.shinkansenFareCalculate(
//                date,
//                seatType,
//                destination,
//                adultPassengerNumber,
//                childPassengerNumber,
//                discountType,
//                shinkansenType,
//                oneWayOrRoundTrip
//        ) >> new Yen(result)
//
//        when:
//        def response = target.invoke(request)
//
//        then:
//        assert response.get("results") == result
//
//        where:
//
//        date                  | seatType          | destination           | adultPassengerNumber | childPassengerNumber | discountType       | shinkansenType        | oneWayOrRoundTrip         | result
//        new Date(2020, 8, 10) | SeatType.FreeSeat | Destination.ShinOsaka | new Number(10)       | new Number(21)       | DiscountType.Group | ShinkansenType.Hikari | OneWayOrRoundTrip.ONE_WAY | 244230
//
//    }
}
