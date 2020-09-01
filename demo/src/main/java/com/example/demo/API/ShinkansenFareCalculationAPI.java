package com.example.demo.API;

import com.example.demo.service.ShinkansenFareCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class ShinkansenFareCalculationAPI {

    @Autowired
    private ShinkansenFareCalculationService service;

    @RequestMapping(value = "jr-pricing")
    public Map invoke(ShinkansenFareRequest request) {

        Map<String, Object> res = new HashMap<>();

        res.put("results",
                service.ShinkansenFareCalculate
                        (
                                request.getDate(),
                                request.getSeatType(),
                                request.getDestination(),
                                request.getAdultPassengerNumber(),
                                request.getChildPassengerNumber(),
                                request.getDiscountType(),
                                request.getShinkansenType(),
                                request.getOneWayOrRoundTrip()
                        ).getValue()
        );

        return res;
    }
}
