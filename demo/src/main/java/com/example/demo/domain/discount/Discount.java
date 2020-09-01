package com.example.demo.domain.discount;

import com.example.demo.domain.Number;

public interface Discount {
    DiscountRate getDiscountRate();
    Number getCalculateAdultNumber();
    Number getCalculateChildNumber();
}
