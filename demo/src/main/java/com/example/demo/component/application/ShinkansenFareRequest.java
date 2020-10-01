package com.example.demo.component.application;

import com.example.demo.component.domain.Date;
import com.example.demo.component.domain.Destination;
import com.example.demo.component.domain.Number;
import com.example.demo.component.domain.OneWayOrRoundTrip;
import com.example.demo.component.domain.discount.DiscountType;
import com.example.demo.component.domain.surcharge.seat.SeatType;
import com.example.demo.component.domain.surcharge.superexpress.ShinkansenType;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
@Data
@Component
public class ShinkansenFareRequest {
    @Autowired
    private DateForm dateForm;
    @Autowired
    private DestinationForm destinationForm;
    @Autowired
    private SeatTypeForm seatTypeForm;
    @Autowired
    private AdultPassengerNumberForm adultPassengerNumberForm;
    @Autowired
    private ChildPassengerNumberForm childPassengerNumberForm;
    @Autowired
    private DiscountTypeForm discountTypeForm;
    @Autowired
    private ShinkansenTypeForm shinkansenTypeForm;
    @Autowired
    private OneWayOrRoundTripForm oneWayOrRoundTripForm;

    @Data
    @Component
    public static class DateForm {

        @Max(2100)
        @Min(1900)
        private int year;
        @Min(1)
        @Max(12)
        private int month;
        @Min(1)
        @Max(31)
        private int day;
    }

    @Data
    @Component
    public static class DestinationForm {
        @NotEmpty(message = "入力してください")
        @Size(min = 1, max = 10)
        private String destination;
    }

    @Data
    @Component
    public static class SeatTypeForm {
        @NotEmpty
        @Size(min = 1, max = 10)
        private String seatType;
    }

    @Data
    @Component
    public static class AdultPassengerNumberForm {
        @Size(min = 0)
        private int adultPassengerNumber;
    }

    @Data
    @Component
    public static class ChildPassengerNumberForm {
        @Size(min = 0)
        private int childPassengerNumber;
    }

    @Data
    @Component
    public static class DiscountTypeForm {
        @NotEmpty
        @Size(min = 1, max = 10)
        private String discountType;
    }

    @Data
    @Component
    public static class ShinkansenTypeForm {
        @NotEmpty
        @Size(min = 1, max = 10)
        private String shinkansenType;
    }

    @Data
    @Component
    public static class OneWayOrRoundTripForm {
        @NotEmpty
        @Size(min = 1, max = 10)
        private String oneWayOrRoundTrip;
    }

    public Date getDate() {
        return new Date(this.dateForm.getYear(), this.dateForm.getMonth(), this.dateForm.getDay());
    }

    public Destination getDestination() {
        return Destination.valueOf(this.destinationForm.getDestination());
    }

    public SeatType getSeatType() {
        return SeatType.valueOf(this.seatTypeForm.getSeatType());
    }

    public Number getAdultPassengerNumber() {
        return new Number(this.adultPassengerNumberForm.getAdultPassengerNumber());
    }

    public Number getChildPassengerNumber() {
        return new Number(this.childPassengerNumberForm.getChildPassengerNumber());
    }

    public DiscountType getDiscountType() {
        return DiscountType.valueOf(this.discountTypeForm.getDiscountType());
    }

    public ShinkansenType getShinkansenType() {
        return ShinkansenType.valueOf(this.shinkansenTypeForm.getShinkansenType());
    }

    public OneWayOrRoundTrip getOneWayOrRoundTrip() {
        return OneWayOrRoundTrip.valueOf(this.oneWayOrRoundTripForm.getOneWayOrRoundTrip());
    }

}