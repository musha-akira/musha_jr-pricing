package com.example.demo.component.domain.discount;

import com.example.demo.component.domain.Date;
import com.example.demo.component.domain.Number;
import com.example.demo.component.domain.PassengerNumber;

public class GroupDiscount implements Discount {

    private static final Number MIN_GROUP_DISCOUNT_PASSENGER_NUMBER = new Number(8);
    private static final Number MIN_FREE_GROUP_DISCOUNT_PASSENGER_NUMBER = new Number(31);
    private static final Number BORDER_FREE_GROUP_DISCOUNT_PASSENGER_NUMBER = new Number(50);
    private static final DiscountRate NEW_YEAR_DISCOUNT_RATE = new DiscountRate(0.1);
    private static final DiscountRate GENERAL_DISCOUNT_RATE = new DiscountRate(0.15);

    private final PassengerNumber passengerNumber;


    private PassengerNumber freePassengerNumber;
    private final DiscountRate groupDiscountRate;

    /*
    ・8人未満ならば例外投げる
    ・newYear(12/21~1/10)ならば割引率10%そうでなければ15%
    ・31人からは　(1+(人数-1)/50)人(小数点切り捨て)が無賃扱人（freePassenger）となる
    ・無賃扱人は大人優先
     */
    public GroupDiscount(PassengerNumber passengerNumber, Date date) {

        if (!canGroupDiscount(passengerNumber)) {
            throw new RuntimeException("団体割引を適用できません。");
        }

        this.passengerNumber = passengerNumber;


        if (date.isNewYear()) {
            this.groupDiscountRate = NEW_YEAR_DISCOUNT_RATE;
        } else {
            this.groupDiscountRate = GENERAL_DISCOUNT_RATE;
        }

        if (canFreePassengerGroupDiscount(this.passengerNumber)) {

            Number freeNumber = createFreeNumber(this.passengerNumber);

            setFreePassengerNumber(freeNumber);
        } else {
            this.freePassengerNumber = new PassengerNumber(new Number(0), new Number(0));
        }
    }

    //MIN_GROUP_DISCOUNT_PASSENGER_NUMBER 以上ならば団体割引が使える
    public boolean canGroupDiscount(PassengerNumber passengerNumber) {
        return passengerNumber.getTotalPassengerNumber().compare(MIN_GROUP_DISCOUNT_PASSENGER_NUMBER) >= 0;
    }

    //MIN_FREE_GROUP_DISCOUNT_PASSENGER_NUMBER 以上ならば無賃扱人割引が使える
    public boolean canFreePassengerGroupDiscount(PassengerNumber passengerNumber) {
        return passengerNumber.getTotalPassengerNumber().compare(MIN_FREE_GROUP_DISCOUNT_PASSENGER_NUMBER) >= 0;
    }

    //(1+(人数-1)/50)人(小数点切り捨て)が無賃扱人（freePassenger）となる
    public Number createFreeNumber(PassengerNumber passengerNumber) {
        return new Number(1).add(
                passengerNumber.getTotalPassengerNumber()
                        .subtract(new Number(1)).divide(BORDER_FREE_GROUP_DISCOUNT_PASSENGER_NUMBER)
        );
    }

    //ここで無賃扱い人数を受け取って、大人、子供に無賃扱いにんを割り振る
    //無賃扱い人数より大人の人数の方が大きければ無賃扱い人は全員大人
    //小さければその分だけ子供が無賃扱い人となる
    private void setFreePassengerNumber(Number freeNumber) {

        Number adultPassengerNumber = this.passengerNumber.getAdultPassengerNumber();

        if (adultPassengerNumber.compare(freeNumber) >= 0) {

            this.freePassengerNumber = new PassengerNumber(freeNumber, new Number(0));
        } else {

            this.freePassengerNumber = new PassengerNumber
                    (
                            adultPassengerNumber,
                            freeNumber.subtract(adultPassengerNumber)
                    );
        }
    }

    @Override
    public DiscountRate getDiscountRate() {
        return this.groupDiscountRate;
    }

    @Override
    public Number getFreePassengerExcludedAdultNumber() {
        return this.passengerNumber.getAdultPassengerNumber()
                .subtract(this.freePassengerNumber.getAdultPassengerNumber());
    }

    @Override
    public Number getFreePassengerExcludedChildNumber() {
        return this.passengerNumber.getChildPassengerNumber()
                .subtract(this.freePassengerNumber.getChildPassengerNumber());
    }
}
