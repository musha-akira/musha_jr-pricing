package com.example.demo.component.domain;

import lombok.Getter;

@Getter
public class Number {
    private final int number;

    public Number(int number) {
        if (number < 0) {
            throw new RuntimeException("人数が適切ではありません。");
        }
        this.number = number;
    }

    public Number add(Number number) {
        return new Number(this.number + number.getNumber());
    }

    public Number subtract(Number number) {
        return new Number(this.number - number.getNumber());
    }

    public Number divide(Number number) {
        return new Number(this.number / number.getNumber());
    }

    public int compare(Number number) {
        return Integer.compare(this.number, number.getNumber());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Number) {
            Number other = (Number) obj;
            return other.getNumber() == this.number;
        }
        return false;
    }
}
