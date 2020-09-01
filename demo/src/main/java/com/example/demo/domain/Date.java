package com.example.demo.domain;


public class Date {
    private int year;
    private int month;
    private int day;

    private static final int DECEMBER = 12;
    private static final int JANUARY = 1;

    private static final int NEW_YEAR_START_DAY = 21;
    private static final int NEW_YEAR_END_DAY = 10;

    public Date(int year, int month, int day) {
        if (month > DECEMBER || day < 1 || day > 31) {
            throw new RuntimeException("日付は存在しません");
        }
    }

    public boolean isNewYear() {
        return (this.month == DECEMBER && day >= NEW_YEAR_START_DAY)
                || (this.month == JANUARY && day <= NEW_YEAR_END_DAY);
    }
}
