package com.example.demo.component.application;

import com.example.demo.component.service.ShinkansenFareCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@ComponentScan("com.example.demo.component.service")
public class ShinkansenFareCalculationAPI {

    @Autowired
    ShinkansenFareCalculationService service;

    @Autowired
    ShinkansenFareRequest request;

    @ModelAttribute
    public ShinkansenFareRequest createRequest() {
        return this.request;
    }

//    @RequestMapping(value = "/jr-pricing", method = RequestMethod.GET)
//    public Map invoke(ShinkansenFareRequest request) {
//
//        Map<String, Object> res = new HashMap<>();
//
//        res.put("results",
//                service.shinkansenFareCalculate
//                        (
//                                request.getDate(),
//                                request.getSeatType(),
//                                request.getDestination(),
//                                request.getAdultPassengerNumber(),
//                                request.getChildPassengerNumber(),
//                                request.getDiscountType(),
//                                request.getShinkansenType(),
//                                request.getOneWayOrRoundTrip()
//
//                        ).getValue()
//        );
//
//        return res;
//    }

    @RequestMapping(value = "/jr-pricing", method = RequestMethod.GET)
    public String input() {
        return "jr-pricing";
    }

    @RequestMapping(value = "/jr-pricingInfo", method = RequestMethod.POST)
    public String output(@Validated ShinkansenFareRequest request, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "jr-pricing";
        }

        Map<String, Object> res = new HashMap<>();

        res.put("results",
                service.shinkansenFareCalculate
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

        model.addAttribute("model", res);
        System.out.println(res.toString());

        return "jr-pricingInfo";
    }
}