package com.example.demo.component.domain;

import lombok.Data;

@Data
public class Date {
    private final int year;
    private final int month;
    private final int day;

    private static final int DECEMBER = 12;
    private static final int JANUARY = 1;
//    private static final int FEBRUARY = 2;

    private static final int NEW_YEAR_START_DAY = 21;
    private static final int NEW_YEAR_END_DAY = 10;

    public Date(int year, int month, int day) {
        if (month > DECEMBER || day < 1 || day > 31) {
            throw new RuntimeException("日付は存在しません。");
        }

        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isNewYear() {
        return (this.month == DECEMBER && day >= NEW_YEAR_START_DAY)
                || (this.month == JANUARY && day <= NEW_YEAR_END_DAY);
    }
}
