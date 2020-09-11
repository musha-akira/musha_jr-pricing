package com.example.demo.component.domain.discount;

import com.example.demo.component.domain.Number;

public interface Discount {
    DiscountRate getDiscountRate();
    Number getCalculateAdultNumber();
    Number getCalculateChildNumber();
}
