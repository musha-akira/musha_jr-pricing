package com.example.demo.component.domain;

import lombok.Getter;

@Getter
public class Date {
    private final int year;
    private final int month;
    private final int day;

    private static final int DECEMBER = 12;
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int APRIL = 4;
    private static final int JUNE = 6;
    private static final int SEPTEMBER = 9;
    private static final int NOVEMBER = 11;

    private static final int LONG_MONTH_LAST_DAY = 31;
    private static final int SHORT_MONTH_LAST_DAY = 30;
    private static final int FEBRUARY_LAST_DAY = 28;

    private static final int NEW_YEAR_START_DAY = 21;
    private static final int NEW_YEAR_END_DAY = 10;

    public Date(int year, int month, int day) {
        if (
                year < 0
                        || month < JANUARY
                        || month > DECEMBER
                        || day < 1
                        || day > getLastDay(year, month)

        ) {
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

    public boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    public int getLastDay(int year, int month) {
        if (month == FEBRUARY && isLeapYear(year)) {
            return FEBRUARY_LAST_DAY + 1;
        }
        if (month == FEBRUARY && !isLeapYear(year)) {
            return FEBRUARY_LAST_DAY;
        }
        if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            return SHORT_MONTH_LAST_DAY;
        }
        return LONG_MONTH_LAST_DAY;
    }
}
